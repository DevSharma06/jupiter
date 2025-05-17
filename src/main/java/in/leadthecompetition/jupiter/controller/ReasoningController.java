package in.leadthecompetition.jupiter.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.leadthecompetition.jupiter.model.ApiResponse;
import in.leadthecompetition.jupiter.model.reasoning.ReasoningCategory;
import in.leadthecompetition.jupiter.model.reasoning.ReasoningQuestion;
import in.leadthecompetition.jupiter.service.ReasoningService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/reasoning")
public class ReasoningController {
	private final ReasoningService service;

	public ReasoningController(ReasoningService service) {
		this.service = service;
	}

	@PostMapping("/addQuestion")
	public ReasoningQuestion insertQuestion(@RequestBody ReasoningQuestion englishDTO) {
		return service.insertOrUpdateQuestion(englishDTO);
	}

	@DeleteMapping("/deleteQuestion/{id}")
	public ApiResponse<ReasoningQuestion> deleteQuestion(@PathVariable Long id) {
		Optional<ReasoningQuestion> optionalQuestion = service.getQuestionByID(id);
		if (optionalQuestion.isPresent()) {
			service.deleteQuestion(id);
			return new ApiResponse<ReasoningQuestion>(true, "Question deleted", optionalQuestion.get(),
					LocalDateTime.now());
		} else {
			return new ApiResponse<ReasoningQuestion>(false, "Question not found", null, "NOT_FOUND",
					LocalDateTime.now());
		}

	}

	@GetMapping("/getQuestion/{id}")
	public ApiResponse<ReasoningQuestion> getQuestion(@PathVariable Long id) {
		Optional<ReasoningQuestion> optionalQuestion = service.getQuestionByID(id);
		if (optionalQuestion.isPresent()) {
			return new ApiResponse<ReasoningQuestion>(true, "Question found", optionalQuestion.get(),
					LocalDateTime.now());
		} else {
			return new ApiResponse<ReasoningQuestion>(false, "Question not found", null, "NOT_FOUND",
					LocalDateTime.now());
		}

	}

	@GetMapping("/getAllQuestions")
	public List<ReasoningQuestion> getAllQuestions() {
		return service.getAllQuestions();
	}

	@PostMapping("/addCategory")
	public ApiResponse<ReasoningCategory> insertQuestion(@RequestBody ReasoningCategory category) {
		try {
			ReasoningCategory addedCategory = service.insertReasoningCategory(category);
			return new ApiResponse<ReasoningCategory>(true, "Category added", addedCategory, LocalDateTime.now());
		} catch (DataIntegrityViolationException de) {
			return new ApiResponse<ReasoningCategory>(true, "Category already exists", null, LocalDateTime.now());
		} catch (Exception e) {
			return new ApiResponse<ReasoningCategory>(true, "An error occurred while saving the category", null,
					LocalDateTime.now());
		}

	}

	@GetMapping("/getCategories")
	public List<ReasoningCategory> getCategories() {
		return service.getCategories();
	}
}
