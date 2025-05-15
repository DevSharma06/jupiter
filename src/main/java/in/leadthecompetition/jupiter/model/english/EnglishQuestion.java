package in.leadthecompetition.jupiter.model.english;

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
@Table(name = "english")
@EntityListeners(AuditingEntityListener.class)
public class EnglishQuestion extends BaseQuestion {

	@OneToMany(mappedBy = "english", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EnglishOption> options;

	public EnglishQuestion() {
		super();
	}

	public EnglishQuestion(Long id, String questionText, String explanation, Difficulty difficulty, int categoryID,
			boolean isActive, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super(id, questionText, explanation, difficulty, categoryID, isActive, createdAt, updatedAt);
	}

	public EnglishQuestion(Long id, String questionText, String explanation, Difficulty difficulty, int categoryID,
			boolean isActive, LocalDateTime createdAt, LocalDateTime updatedAt, List<EnglishOption> options) {
		super(id, questionText, explanation, difficulty, categoryID, isActive, createdAt, updatedAt);
		this.options = options;
	}

	public List<EnglishOption> getOptions() {
		return options;
	}

	public void setOptions(List<EnglishOption> options) {
		this.options = options;
		if (options != null) {
			options.forEach(option -> option.setEnglish(this));
		}
	}

}