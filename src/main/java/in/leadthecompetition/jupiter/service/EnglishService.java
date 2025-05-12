package in.leadthecompetition.jupiter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.leadthecompetition.jupiter.model.english.EnglishCategory;
import in.leadthecompetition.jupiter.model.english.EnglishQuestion;
import in.leadthecompetition.jupiter.model.english.EnglishOption;
import in.leadthecompetition.jupiter.repository.english.EnglishCategoryRepository;
import in.leadthecompetition.jupiter.repository.english.EnglishRepository;

@Service
public class EnglishService {

	@Autowired
	private EnglishRepository englishRepository;
	@Autowired
	private EnglishCategoryRepository categoryRepository;

	public EnglishQuestion insertOrUpdateQuestion(EnglishQuestion question) {
		EnglishQuestion english = new EnglishQuestion();
		english.setQuestionText(question.getQuestionText());
		english.setExplanation(question.getExplanation());
		english.setDifficulty(question.getDifficulty());
		english.setCategoryID(question.getCategoryID());
		english.setActive(true);

		List<EnglishOption> options = question.getOptions();
		for (EnglishOption opt : options) {
			opt.setEnglish(english);
		}
		english.setOptions(options);

		return englishRepository.save(english);
	}

	public void deleteQuestion(Long id) {
		englishRepository.deleteById(id);
	}

	public Optional<EnglishQuestion> getQuestionByID(Long questionID) {
		return englishRepository.findById(questionID);
	}

	public List<EnglishQuestion> getAllQuestions() {
		return englishRepository.findAll();
	}

	public EnglishCategory insertEnglishCategory(EnglishCategory category) {
		return categoryRepository.save(category);
	}

	public List<EnglishCategory> getCategories() {
		return categoryRepository.findAll();
	}
}
