package in.leadthecompetition.jupiter.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.leadthecompetition.jupiter.model.ApiResponse;
import in.leadthecompetition.jupiter.model.qa.QACategory;
import in.leadthecompetition.jupiter.model.qa.QAQuestion;
import in.leadthecompetition.jupiter.service.QAService;

@RestController
@RequestMapping("/api/qa")
public class QAController {
	private final QAService service;

	public QAController(QAService service) {
		this.service = service;
	}

	@PostMapping("/addQuestion")
	public QAQuestion insertQuestion(@RequestBody QAQuestion qaDTO) {
		return service.insertOrUpdateQuestion(qaDTO);
	}

	@DeleteMapping("/deleteQuestion/{id}")
	public ApiResponse<QAQuestion> deleteQuestion(@PathVariable Long id) {
		Optional<QAQuestion> optionalQuestion = service.getQuestionByID(id);
		if (optionalQuestion.isPresent()) {
			service.deleteQuestion(id);
			return new ApiResponse<QAQuestion>(true, "Question deleted", optionalQuestion.get(), LocalDateTime.now());
		} else {
			return new ApiResponse<QAQuestion>(false, "Question not found", null, "NOT_FOUND", LocalDateTime.now());
		}
	}

	@GetMapping("/getQuestion/{id}")
	public ApiResponse<QAQuestion> getQuestion(@PathVariable Long id) {
		Optional<QAQuestion> optionalQuestion = service.getQuestionByID(id);
		if (optionalQuestion.isPresent()) {
			return new ApiResponse<QAQuestion>(true, "Question found", optionalQuestion.get(), LocalDateTime.now());
		} else {
			return new ApiResponse<QAQuestion>(false, "Question not found", null, "NOT_FOUND", LocalDateTime.now());
		}
	}

	@GetMapping("/getAllQuestions")
	public List<QAQuestion> getAllQuestions() {
		return service.getAllQuestions();
	}

	@PostMapping("/addCategory")
	public ApiResponse<QACategory> insertQuestion(@RequestBody QACategory category) {
		try {
			QACategory addedCategory = service.insertQACategory(category);
			return new ApiResponse<QACategory>(true, "Category added", addedCategory, LocalDateTime.now());
		} catch (DataIntegrityViolationException de) {
			return new ApiResponse<QACategory>(true, "Category already exists", null, LocalDateTime.now());
		} catch (Exception e) {
			return new ApiResponse<QACategory>(true, "An error occurred while saving the category", null,
					LocalDateTime.now());
		}

	}

	@GetMapping("/getCategories")
	public List<QACategory> getCategories() {
		return service.getCategories();
	}
}
