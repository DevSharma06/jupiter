package in.leadthecompetition.jupiter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.leadthecompetition.jupiter.model.ERole;
import in.leadthecompetition.jupiter.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
