package in.leadthecompetition.jupiter.model.english;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import in.leadthecompetition.jupiter.model.base.BaseCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;

@Entity
@Table(name = "english_categories")
@EntityListeners(AuditingEntityListener.class)
public class EnglishCategory extends BaseCategory {

	public EnglishCategory(Long id, String categoryName, int questionCount, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		super(id, categoryName, questionCount, createdAt, updatedAt);
	}

}
