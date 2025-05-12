package in.leadthecompetition.jupiter.repository.english;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.leadthecompetition.jupiter.model.english.EnglishQuestion;

@Repository
public interface EnglishRepository extends JpaRepository<EnglishQuestion, Long> {

}
