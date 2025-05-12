package in.leadthecompetition.jupiter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.leadthecompetition.jupiter.model.qa.QACategory;
import in.leadthecompetition.jupiter.model.qa.QAOption;
import in.leadthecompetition.jupiter.model.qa.QAQuestion;
import in.leadthecompetition.jupiter.repository.qa.QACategoryRepository;
import in.leadthecompetition.jupiter.repository.qa.QARepository;

@Service
public class QAService {
	@Autowired
	private QARepository qaRepository;
	@Autowired
	private QACategoryRepository categoryRepository;

	public QAQuestion insertOrUpdateQuestion(QAQuestion question) {
		QAQuestion qa = new QAQuestion();
		qa.setQuestionText(question.getQuestionText());
		qa.setExplanation(question.getExplanation());
		qa.setDifficulty(question.getDifficulty());
		qa.setCategoryID(question.getCategoryID());
		qa.setActive(true);

		List<QAOption> options = question.getOptions();
		for (QAOption opt : options) {
			opt.setQaQuestion(qa);
		}
		qa.setOptions(options);

		return qaRepository.save(qa);
	}

	public void deleteQuestion(Long id) {
		qaRepository.deleteById(id);
	}

	public Optional<QAQuestion> getQuestionByID(Long questionID) {
		return qaRepository.findById(questionID);
	}

	public List<QAQuestion> getAllQuestions() {
		return qaRepository.findAll();
	}

	public QACategory insertQACategory(QACategory category) {
		return categoryRepository.save(category);
	}

	public List<QACategory> getCategories() {
		return categoryRepository.findAll();
	}
}
