package in.leadthecompetition.jupiter.repository.qa;

import org.springframework.data.jpa.repository.JpaRepository;

import in.leadthecompetition.jupiter.model.qa.QAQuestion;

public interface QARepository extends JpaRepository<QAQuestion, Long> {

}
