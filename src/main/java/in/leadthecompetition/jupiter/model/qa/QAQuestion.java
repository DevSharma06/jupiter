package in.leadthecompetition.jupiter.model.qa;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import in.leadthecompetition.jupiter.model.Difficulty;
import in.leadthecompetition.jupiter.model.base.BaseQuestion;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "quantitative_aptitude")
@EntityListeners(AuditingEntityListener.class)
public class QAQuestion extends BaseQuestion {

	@OneToMany(mappedBy = "qaQuestion", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<QAOption> options;

	public QAQuestion() {
		super();
	}

	public QAQuestion(Long id, String questionText, String explanation, Difficulty difficulty, int categoryID,
			boolean isActive, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super(id, questionText, explanation, difficulty, categoryID, isActive, createdAt, updatedAt);
	}

	public QAQuestion(Long id, String questionText, String explanation, Difficulty difficulty, int categoryID,
			boolean isActive, LocalDateTime createdAt, LocalDateTime updatedAt, List<QAOption> options) {
		super(id, questionText, explanation, difficulty, categoryID, isActive, createdAt, updatedAt);
		this.options = options;
	}

	public List<QAOption> getOptions() {
		return options;
	}

	public void setOptions(List<QAOption> options) {
		this.options = options;
	}

}
