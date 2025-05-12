package in.leadthecompetition.jupiter.repository.qa;

import org.springframework.data.jpa.repository.JpaRepository;

import in.leadthecompetition.jupiter.model.qa.QACategory;

public interface QACategoryRepository extends JpaRepository<QACategory, Long> {

}
