package in.leadthecompetition.jupiter.repository.english;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.leadthecompetition.jupiter.model.english.EnglishOption;

@Repository
public interface EnglishOptionRepository extends JpaRepository<EnglishOption, Long> {

}
