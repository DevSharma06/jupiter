package in.leadthecompetition.jupiter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.leadthecompetition.jupiter.model.ga.GACategory;
import in.leadthecompetition.jupiter.model.ga.GAOption;
import in.leadthecompetition.jupiter.model.ga.GAQuestion;
import in.leadthecompetition.jupiter.repository.ga.GACategoryRepository;
import in.leadthecompetition.jupiter.repository.ga.GARepository;

@Service
public class GAService {
	@Autowired
	private GARepository gaRepository;
	@Autowired
	private GACategoryRepository categoryRepository;

	public GAQuestion insertOrUpdateQuestion(GAQuestion question) {
		GAQuestion ga = new GAQuestion();
		ga.setQuestionText(question.getQuestionText());
		ga.setExplanation(question.getExplanation());
		ga.setDifficulty(question.getDifficulty());
		ga.setCategoryID(question.getCategoryID());
		ga.setActive(true);

		List<GAOption> options = question.getOptions();
		for (GAOption opt : options) {
			opt.setGaQuestion(ga);
		}
		ga.setOptions(options);

		return gaRepository.save(ga);
	}

	public void deleteQuestion(Long id) {
		gaRepository.deleteById(id);
	}

	public Optional<GAQuestion> getQuestionByID(Long questionID) {
		return gaRepository.findById(questionID);
	}

	public List<GAQuestion> getAllQuestions() {
		return gaRepository.findAll();
	}

	public GACategory insertGACategory(GACategory category) {
		return categoryRepository.save(category);
	}

	public List<GACategory> getCategories() {
		return categoryRepository.findAll();
	}
}
