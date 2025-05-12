package in.leadthecompetition.jupiter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.leadthecompetition.jupiter.model.reasoning.ReasoningCategory;
import in.leadthecompetition.jupiter.model.reasoning.ReasoningOption;
import in.leadthecompetition.jupiter.model.reasoning.ReasoningQuestion;
import in.leadthecompetition.jupiter.repository.reasoning.ReasoningCategoryRepository;
import in.leadthecompetition.jupiter.repository.reasoning.ReasoningRepository;

@Service
public class ReasoningService {
	@Autowired
	private ReasoningRepository reasoningRepository;
	@Autowired
	private ReasoningCategoryRepository categoryRepository;

	public ReasoningQuestion insertOrUpdateQuestion(ReasoningQuestion question) {
		ReasoningQuestion reasoning = new ReasoningQuestion();
		reasoning.setQuestionText(question.getQuestionText());
		reasoning.setExplanation(question.getExplanation());
		reasoning.setDifficulty(question.getDifficulty());
		reasoning.setCategoryID(question.getCategoryID());
		reasoning.setActive(true);

		List<ReasoningOption> options = question.getOptions();
		for (ReasoningOption opt : options) {
			opt.setReasoning(reasoning);
		}
		reasoning.setOptions(options);

		return reasoningRepository.save(reasoning);
	}

	public void deleteQuestion(Long id) {
		reasoningRepository.deleteById(id);
	}

	public Optional<ReasoningQuestion> getQuestionByID(Long questionID) {
		return reasoningRepository.findById(questionID);
	}

	public List<ReasoningQuestion> getAllQuestions() {
		return reasoningRepository.findAll();
	}

	public ReasoningCategory insertReasoningCategory(ReasoningCategory category) {
		return categoryRepository.save(category);
	}

	public List<ReasoningCategory> getCategories() {
		return categoryRepository.findAll();
	}
}
