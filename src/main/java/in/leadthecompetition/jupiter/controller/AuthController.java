package in.leadthecompetition.jupiter.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.leadthecompetition.jupiter.model.ERole;
import in.leadthecompetition.jupiter.model.Role;
import in.leadthecompetition.jupiter.model.User;
import in.leadthecompetition.jupiter.model.payload.request.LoginRequest;
import in.leadthecompetition.jupiter.model.payload.request.SignupRequest;
import in.leadthecompetition.jupiter.model.payload.response.MessageResponse;
import in.leadthecompetition.jupiter.model.payload.response.UserInfoResponse;
import in.leadthecompetition.jupiter.repository.RoleRepository;
import in.leadthecompetition.jupiter.repository.UserRepository;
import in.leadthecompetition.jupiter.security.jwt.JwtUtils;
import in.leadthecompetition.jupiter.security.services.UserDetailsImpl;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		try {

			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

			String jwtToken = jwtUtils.generateToken(userDetails);

			List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
					.collect(Collectors.toList());

//		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(
//				new UserInfoResponse(userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));

			UserInfoResponse userInfoResponse = new UserInfoResponse(userDetails.getUsername(), userDetails.getEmail(),
					roles, jwtToken, jwtUtils.getExpirationTime());

			return ResponseEntity.ok(userInfoResponse);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new String("Bad Credentials"));
		}
	}

//	@PostMapping("/signout")
//	public ResponseEntity<?> logoutUser() {
//		ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
//		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
//				.body(new MessageResponse("You've been signed out!"));
//	}
}
