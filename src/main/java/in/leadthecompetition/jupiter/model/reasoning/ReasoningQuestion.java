package in.leadthecompetition.jupiter.model.reasoning;

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
@Table(name = "reasoning")
@EntityListeners(AuditingEntityListener.class)
public class ReasoningQuestion extends BaseQuestion {

	@OneToMany(mappedBy = "reasoning", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ReasoningOption> options;

	public ReasoningQuestion() {
		super();
	}

	public ReasoningQuestion(Long id, String questionText, String explanation, Difficulty difficulty, int categoryID,
			boolean isActive, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super(id, questionText, explanation, difficulty, categoryID, isActive, createdAt, updatedAt);
	}

	public ReasoningQuestion(Long id, String questionText, String explanation, Difficulty difficulty, int categoryID,
			boolean isActive, LocalDateTime createdAt, LocalDateTime updatedAt, List<ReasoningOption> options) {
		super(id, questionText, explanation, difficulty, categoryID, isActive, createdAt, updatedAt);
		this.options = options;
	}

	public List<ReasoningOption> getOptions() {
		return options;
	}

	public void setOptions(List<ReasoningOption> options) {
		this.options = options;
	}

}
