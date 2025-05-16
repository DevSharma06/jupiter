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
import in.leadthecompetition.jupiter.model.ga.GACategory;
import in.leadthecompetition.jupiter.model.ga.GAQuestion;
import in.leadthecompetition.jupiter.service.GAService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/ga")
public class GAController {
	private final GAService service;

	public GAController(GAService service) {
		this.service = service;
	}

	@PostMapping("/addQuestion")
	public GAQuestion insertQuestion(@RequestBody GAQuestion gaDTO) {
		return service.insertOrUpdateQuestion(gaDTO);
	}

	@DeleteMapping("/deleteQuestion/{id}")
	public ApiResponse<GAQuestion> deleteQuestion(@PathVariable Long id) {
		Optional<GAQuestion> optionalQuestion = service.getQuestionByID(id);
		if (optionalQuestion.isPresent()) {
			service.deleteQuestion(id);
			return new ApiResponse<GAQuestion>(true, "Question deleted", optionalQuestion.get(), LocalDateTime.now());
		} else {
			return new ApiResponse<GAQuestion>(false, "Question not found", null, "NOT_FOUND", LocalDateTime.now());
		}

	}

	@GetMapping("/getQuestion/{id}")
	public ApiResponse<GAQuestion> getQuestion(@PathVariable Long id) {
		Optional<GAQuestion> optionalQuestion = service.getQuestionByID(id);
		if (optionalQuestion.isPresent()) {
			return new ApiResponse<GAQuestion>(true, "Question found", optionalQuestion.get(), LocalDateTime.now());
		} else {
			return new ApiResponse<GAQuestion>(false, "Question not found", null, "NOT_FOUND", LocalDateTime.now());
		}
	}

	@GetMapping("/getAllQuestions")
	public List<GAQuestion> getAllQuestions() {
		return service.getAllQuestions();
	}

	@PostMapping("/addCategory")
	public ApiResponse<GACategory> insertQuestion(@RequestBody GACategory category) {
		try {
			GACategory addedCategory = service.insertGACategory(category);
			return new ApiResponse<GACategory>(true, "Category added", addedCategory, LocalDateTime.now());
		} catch (DataIntegrityViolationException de) {
			return new ApiResponse<GACategory>(true, "Category already exists", null, LocalDateTime.now());
		} catch (Exception e) {
			return new ApiResponse<GACategory>(true, "An error occurred while saving the category", null,
					LocalDateTime.now());
		}

	}

	@GetMapping("/getCategories")
	public List<GACategory> getCategories() {
		return service.getCategories();
	}
}
