package in.leadthecompetition.jupiter.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "english")
@EntityListeners(AuditingEntityListener.class)
public class EnglishDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String questionText;

	private String explanation;

	@Enumerated(EnumType.STRING)
	private Difficulty difficulty;

	private int categoryID;

	private boolean isActive;

	@OneToMany(mappedBy = "english", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EnglishOption> options;

	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate
	private LocalDateTime updatedAt;

	public EnglishDTO() {
		super();
	}

	public EnglishDTO(Long id, String questionText, String explanation, Difficulty difficulty, int categoryID,
			boolean isActive, List<EnglishOption> options, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.questionText = questionText;
		this.explanation = explanation;
		this.difficulty = difficulty;
		this.categoryID = categoryID;
		this.isActive = isActive;
		this.options = options;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
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

enum Difficulty {
	EASY, MEDIUM, HARD
}