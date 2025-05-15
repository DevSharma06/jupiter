package in.leadthecompetition.jupiter.model.ga;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import in.leadthecompetition.jupiter.model.base.BaseCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;

@Entity
@Table(name = "ga_categories")
@EntityListeners(AuditingEntityListener.class)
public class GACategory extends BaseCategory {

	public GACategory(Long id, String categoryName, int questionCount, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		super(id, categoryName, questionCount, createdAt, updatedAt);
	}

	public GACategory(Long id, String categoryName, int questionCount) {
		super(id, categoryName, questionCount);
	}

	public GACategory(String categoryName, int questionCount) {
		super(categoryName, questionCount);
	}
}
