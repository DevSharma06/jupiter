package in.leadthecompetition.jupiter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.leadthecompetition.jupiter.model.EnglishCategory;
import in.leadthecompetition.jupiter.model.EnglishDTO;
import in.leadthecompetition.jupiter.model.EnglishOption;
import in.leadthecompetition.jupiter.repository.EnglishCategoryRepository;
import in.leadthecompetition.jupiter.repository.EnglishOptionRepository;
import in.leadthecompetition.jupiter.repository.EnglishRepository;
import jakarta.transaction.Transactional;

@Service
public class EnglishService {

	@Autowired
	private EnglishRepository englishRepository;
	@Autowired
	private EnglishOptionRepository optionsRepository;
	@Autowired
	private EnglishCategoryRepository categoryRepository;

	public EnglishDTO insertOrUpdateQuestion(EnglishDTO question) {
		EnglishDTO english = new EnglishDTO();
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

	public Optional<EnglishDTO> getQuestionByID(Long questionID) {
		return englishRepository.findById(questionID);
	}

	public List<EnglishDTO> getAllQuestions() {
		return englishRepository.findAll();
	}

	public List<EnglishOption> getOptions(Long questionID) {
		return optionsRepository.getQuestionOptions(questionID);
	}

	@Transactional
	public void deleteOptions(Long questionID) {
		optionsRepository.deleteOptionsOfQuestion(questionID);
	}

	public EnglishCategory insertEnglishCategory(EnglishCategory category) {
		return categoryRepository.save(category);
	}

	public List<EnglishCategory> getCategories() {
		return categoryRepository.findAll();
	}
}
