package in.leadthecompetition.jupiter.repository.ga;

import org.springframework.data.jpa.repository.JpaRepository;

import in.leadthecompetition.jupiter.model.ga.GAQuestion;

public interface GARepository extends JpaRepository<GAQuestion, Long> {

}
