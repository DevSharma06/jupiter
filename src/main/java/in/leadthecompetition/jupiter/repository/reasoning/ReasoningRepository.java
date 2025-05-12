package in.leadthecompetition.jupiter.repository.reasoning;

import org.springframework.data.jpa.repository.JpaRepository;

import in.leadthecompetition.jupiter.model.reasoning.ReasoningQuestion;

public interface ReasoningRepository extends JpaRepository<ReasoningQuestion, Long> {

}
