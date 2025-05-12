package in.leadthecompetition.jupiter.model.reasoning;

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
@Table(name = "reasoning_options")
@EntityListeners(AuditingEntityListener.class)
public class ReasoningOption extends BaseOption {

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "questionId", referencedColumnName = "id")
	@JsonIgnore
	private ReasoningQuestion reasoning;

	public ReasoningOption() {
		super();
	}

	public ReasoningOption(Long id, String optionText, boolean isCorrect, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		super(id, optionText, isCorrect, createdAt, updatedAt);
	}

	public ReasoningOption(Long id, String optionText, boolean isCorrect, LocalDateTime createdAt,
			LocalDateTime updatedAt, ReasoningQuestion reasoning) {
		super(id, optionText, isCorrect, createdAt, updatedAt);
		this.reasoning = reasoning;
	}

	public ReasoningQuestion getReasoning() {
		return reasoning;
	}

	public void setReasoning(ReasoningQuestion reasoning) {
		this.reasoning = reasoning;
	}

}
