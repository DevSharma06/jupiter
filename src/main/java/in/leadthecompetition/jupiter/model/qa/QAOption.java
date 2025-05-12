package in.leadthecompetition.jupiter.model.qa;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import in.leadthecompetition.jupiter.model.base.BaseOption;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "qa_options")
@EntityListeners(AuditingEntityListener.class)
public class QAOption extends BaseOption {

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "questionId", referencedColumnName = "id")
	@JsonIgnore
	private QAQuestion qaQuestion;

	public QAOption() {
		super();
	}

	public QAOption(Long id, String optionText, boolean isCorrect, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super(id, optionText, isCorrect, createdAt, updatedAt);
	}

	public QAOption(Long id, String optionText, boolean isCorrect, LocalDateTime createdAt, LocalDateTime updatedAt,
			QAQuestion qaQuestion) {
		super(id, optionText, isCorrect, createdAt, updatedAt);
		this.qaQuestion = qaQuestion;
	}

	public QAQuestion getQaQuestion() {
		return qaQuestion;
	}

	public void setQaQuestion(QAQuestion qaQuestion) {
		this.qaQuestion = qaQuestion;
	}

}
