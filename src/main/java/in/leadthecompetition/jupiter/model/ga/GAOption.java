package in.leadthecompetition.jupiter.model.ga;

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
@Table(name = "ga_options")
@EntityListeners(AuditingEntityListener.class)
public class GAOption extends BaseOption {

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "questionId", referencedColumnName = "id")
	@JsonIgnore
	private GAQuestion gaQuestion;

	public GAOption() {
		super();
	}

	public GAOption(Long id, String optionText, boolean isCorrect, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super(id, optionText, isCorrect, createdAt, updatedAt);
	}

	public GAOption(Long id, String optionText, boolean isCorrect, LocalDateTime createdAt, LocalDateTime updatedAt,
			GAQuestion gaQuestion) {
		super(id, optionText, isCorrect, createdAt, updatedAt);
		this.gaQuestion = gaQuestion;
	}

	public GAQuestion getGaQuestion() {
		return gaQuestion;
	}

	public void setGaQuestion(GAQuestion gaQuestion) {
		this.gaQuestion = gaQuestion;
	}

}
