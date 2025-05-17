package in.leadthecompetition.jupiter.utils;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import in.leadthecompetition.jupiter.model.ERole;
import in.leadthecompetition.jupiter.model.Role;
import in.leadthecompetition.jupiter.model.english.EnglishCategory;
import in.leadthecompetition.jupiter.model.ga.GACategory;
import in.leadthecompetition.jupiter.model.qa.QACategory;
import in.leadthecompetition.jupiter.model.reasoning.ReasoningCategory;
import in.leadthecompetition.jupiter.repository.RoleRepository;
import in.leadthecompetition.jupiter.repository.english.EnglishCategoryRepository;
import in.leadthecompetition.jupiter.repository.ga.GACategoryRepository;
import in.leadthecompetition.jupiter.repository.qa.QACategoryRepository;
import in.leadthecompetition.jupiter.repository.reasoning.ReasoningCategoryRepository;

@Component
public class DataInitializer implements CommandLineRunner {

	private final EnglishCategoryRepository englishRepo;
	private final ReasoningCategoryRepository reasoningRepo;
	private final QACategoryRepository qaRepo;
	private final GACategoryRepository gaRepo;
	private final RoleRepository roleRepo;

	public DataInitializer(EnglishCategoryRepository englishRepo, ReasoningCategoryRepository reasoningRepo,
			QACategoryRepository qaRepo, GACategoryRepository gaRepo, RoleRepository roleRepo) {
		this.englishRepo = englishRepo;
		this.reasoningRepo = reasoningRepo;
		this.qaRepo = qaRepo;
		this.gaRepo = gaRepo;
		this.roleRepo = roleRepo;
	}

	@Override
	public void run(String... args) throws Exception {
		insertEnglishCategories();
		insertReasoningCategories();
		insertQACategories();
		insertGACategories();
		insertRoles();
	}

	private void insertEnglishCategories() {
		if (englishRepo.count() == 0) {
			englishRepo.saveAll(List.of(new EnglishCategory("Synonyms", 2), new EnglishCategory("Antonyms", 2),
					new EnglishCategory("Idioms", 3), new EnglishCategory("Correction of Sentences", 4),
					new EnglishCategory("FITB with suitable words", 3), new EnglishCategory("Spelling", 1),
					new EnglishCategory("Substitutes", 2), new EnglishCategory("Sequence", 3),
					new EnglishCategory("Fill with correct form of verb", 3), new EnglishCategory("Voice", 2)));
		}
	}

	private void insertReasoningCategories() {
		if (reasoningRepo.count() == 0) {
			reasoningRepo.saveAll(List.of(new ReasoningCategory("Alphabetical Series", 2),
					new ReasoningCategory("Distances", 2), new ReasoningCategory("Words which can be formed", 1),
					new ReasoningCategory("Missing number in figures", 1), new ReasoningCategory("Numerical Series", 2),
					new ReasoningCategory("Cryptic Equations", 1), new ReasoningCategory("Meaningful Order", 1),
					new ReasoningCategory("Word Relations", 2), new ReasoningCategory("Wrong Number in Series", 1),
					new ReasoningCategory("Relations", 2), new ReasoningCategory("Logical Reasoning", 2),
					new ReasoningCategory("Directions", 2), new ReasoningCategory("Folded Paper - Punched Holes", 1),
					new ReasoningCategory("Positions", 1), new ReasoningCategory("Groups of Persons", 1),
					new ReasoningCategory("Odd One in the Group", 1), new ReasoningCategory("Hidden Equations", 1),
					new ReasoningCategory("Coded Words", 1)));
		}
	}

	private void insertQACategories() {
		if (qaRepo.count() == 0) {
			qaRepo.saveAll(List.of(new QACategory("Time and Work", 2), new QACategory("Discounts", 1),
					new QACategory("Ratio and Proportions", 1), new QACategory("Profit and Loss", 2),
					new QACategory("Time and Distance", 2), new QACategory("Interest", 2), new QACategory("Algebra", 5),
					new QACategory("Triangles", 2), new QACategory("Circles", 2), new QACategory("Trigonometry", 2),
					new QACategory("Averages", 1), new QACategory("Heights and Distances", 1),
					new QACategory("Solids", 2)));
		}
	}

	private void insertGACategories() {
		if (gaRepo.count() == 0) {
			gaRepo.saveAll(List.of(new GACategory("Awards", 1), new GACategory("Anatomy", 1),
					new GACategory("Animals/Plants", 1), new GACategory("Chemistry", 1),
					new GACategory("Constitution", 2), new GACategory("IT", 1), new GACategory("Culture", 1),
					new GACategory("Diseases", 1), new GACategory("Economics", 1), new GACategory("Geography", 1),
					new GACategory("History H", 1), new GACategory("History M", 1), new GACategory("History B", 2),
					new GACategory("Physics", 1), new GACategory("Religion", 1), new GACategory("Sports", 1),
					new GACategory("States", 1), new GACategory("Miscellaneous", 3),
					new GACategory("Current Affairs", 3)));
		}
	}

	private void insertRoles() {
		if (roleRepo.count() == 0) {
			roleRepo.saveAll(
					List.of(new Role(ERole.ROLE_USER), new Role(ERole.ROLE_MODERATOR), new Role(ERole.ROLE_ADMIN)));
		}
	}
}
