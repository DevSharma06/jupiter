package in.leadthecompetition.jupiter.model.ga;

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
@Table(name = "general_awareness")
@EntityListeners(AuditingEntityListener.class)
public class GAQuestion extends BaseQuestion {

	@OneToMany(mappedBy = "gaQuestion", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<GAOption> options;

	public GAQuestion() {
		super();
	}

	public GAQuestion(Long id, String questionText, String explanation, Difficulty difficulty, int categoryID,
			boolean isActive, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super(id, questionText, explanation, difficulty, categoryID, isActive, createdAt, updatedAt);
	}

	public GAQuestion(Long id, String questionText, String explanation, Difficulty difficulty, int categoryID,
			boolean isActive, LocalDateTime createdAt, LocalDateTime updatedAt, List<GAOption> options) {
		super(id, questionText, explanation, difficulty, categoryID, isActive, createdAt, updatedAt);
		this.options = options;
	}

	public List<GAOption> getOptions() {
		return options;
	}

	public void setOptions(List<GAOption> options) {
		this.options = options;
	}

}
