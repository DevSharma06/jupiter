package in.leadthecompetition.jupiter.model.qa;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import in.leadthecompetition.jupiter.model.base.BaseCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;

@Entity
@Table(name = "qa_categories")
@EntityListeners(AuditingEntityListener.class)
public class QACategory extends BaseCategory {

	public QACategory() {
		super();
	}

	public QACategory(Long id, String categoryName, int questionCount, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		super(id, categoryName, questionCount, createdAt, updatedAt);
	}

	public QACategory(Long id, String categoryName, int questionCount) {
		super(id, categoryName, questionCount);
	}

	public QACategory(String categoryName, int questionCount) {
		super(categoryName, questionCount);
	}
}
