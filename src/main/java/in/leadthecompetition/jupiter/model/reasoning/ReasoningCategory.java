package in.leadthecompetition.jupiter.model.reasoning;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import in.leadthecompetition.jupiter.model.base.BaseCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;

@Entity
@Table(name = "reasoning_categories")
@EntityListeners(AuditingEntityListener.class)
public class ReasoningCategory extends BaseCategory {

	public ReasoningCategory(Long id, String categoryName, int questionCount, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		super(id, categoryName, questionCount, createdAt, updatedAt);
	}

}
