package in.leadthecompetition.jupiter.model.payload.response;

import java.util.List;

public class UserInfoResponse {
	private String username;
	private String email;
	private List<String> roles;
	private String token;
	private long expiresIn;

	public UserInfoResponse(String username, String email, List<String> roles, String token, long expiresIn) {
		super();

		this.username = username;
		this.email = email;
		this.roles = roles;
		this.token = token;
		this.expiresIn = expiresIn;
	}

	public UserInfoResponse(String username, String email, List<String> roles) {

		this.username = username;
		this.email = email;
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}
