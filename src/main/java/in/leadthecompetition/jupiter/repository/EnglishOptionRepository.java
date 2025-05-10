package in.leadthecompetition.jupiter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.leadthecompetition.jupiter.model.EnglishOption;

@Repository
public interface EnglishOptionRepository extends JpaRepository<EnglishOption, Long> {

	@Query(value = "SELECT * FROM english_options WHERE question_id = :questionID", nativeQuery = true)
	List<EnglishOption> getQuestionOptions(@Param("questionID") Long questionID);

	@Modifying
	@Query(value = "DELETE FROM english_options WHERE question_id = :questionID", nativeQuery = true)
	void deleteOptionsOfQuestion(@Param("questionID") Long questionID);
}
