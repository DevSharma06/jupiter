package in.leadthecompetition.jupiter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.leadthecompetition.jupiter.model.EnglishCategory;

@Repository
public interface EnglishCategoryRepository extends JpaRepository<EnglishCategory, Long> {

}
