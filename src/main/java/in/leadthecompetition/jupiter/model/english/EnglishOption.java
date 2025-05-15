package in.leadthecompetition.jupiter.model.english;

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
@Table(name = "english_options")
@EntityListeners(AuditingEntityListener.class)
public class EnglishOption extends BaseOption {

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "questionId", referencedColumnName = "id")
	@JsonIgnore
	private EnglishQuestion english;

	public EnglishOption() {
		super();
	}

	public EnglishOption(Long id, String optionText, boolean isCorrect, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		super(id, optionText, isCorrect, createdAt, updatedAt);
	}

	public EnglishOption(Long id, String optionText, boolean isCorrect, LocalDateTime createdAt,
			LocalDateTime updatedAt, EnglishQuestion english) {
		super(id, optionText, isCorrect, createdAt, updatedAt);
		this.english = english;
	}

	public EnglishQuestion getEnglish() {
		return english;
	}

	public void setEnglish(EnglishQuestion english) {
		this.english = english;
	}

}
