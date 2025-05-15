package in.leadthecompetition.jupiter.model.base;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String categoryName;

	private int questionCount;

	@CreatedDate
	@Column(updatable = false)

	private LocalDateTime createdAt;

	@LastModifiedDate
	private LocalDateTime updatedAt;

	public BaseCategory(Long id, String categoryName, int questionCount, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.questionCount = questionCount;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public BaseCategory(Long id, String categoryName, int questionCount) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.questionCount = questionCount;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	public BaseCategory(String categoryName, int questionCount) {
		super();
		this.categoryName = categoryName;
		this.questionCount = questionCount;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
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

}
