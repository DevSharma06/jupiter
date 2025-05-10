package in.leadthecompetition.jupiter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.leadthecompetition.jupiter.model.EnglishDTO;

@Repository
public interface EnglishRepository extends JpaRepository<EnglishDTO, Long> {

}
