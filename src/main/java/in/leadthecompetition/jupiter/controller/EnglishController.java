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
import in.leadthecompetition.jupiter.model.EnglishCategory;
import in.leadthecompetition.jupiter.model.EnglishDTO;
import in.leadthecompetition.jupiter.service.EnglishService;

@RestController
@RequestMapping("/api/english")
public class EnglishController {

	private final EnglishService service;

	public EnglishController(EnglishService service) {
		this.service = service;
	}

	@PostMapping("/addQuestion")
	public EnglishDTO insertQuestion(@RequestBody EnglishDTO englishDTO) {
		return service.insertOrUpdateQuestion(englishDTO);
	}

	@DeleteMapping("/deleteQuestion/{id}")
	public ApiResponse<EnglishDTO> deleteQuestion(@PathVariable Long id) {
		Optional<EnglishDTO> optionalQuestion = service.getQuestionByID(id);
		if (optionalQuestion.isPresent()) {
			service.deleteQuestion(id);
			return new ApiResponse<EnglishDTO>(true, "Question deleted", optionalQuestion.get(), LocalDateTime.now());
		} else {
			return new ApiResponse<EnglishDTO>(false, "Question not found", null, "NOT_FOUND", LocalDateTime.now());
		}

	}

	@GetMapping("/getQuestion/{id}")
	public ApiResponse<EnglishDTO> getQuestion(@PathVariable Long id) {
		Optional<EnglishDTO> optionalQuestion = service.getQuestionByID(id);
		if (optionalQuestion.isPresent()) {
			return new ApiResponse<EnglishDTO>(true, "Question found", optionalQuestion.get(), LocalDateTime.now());
		} else {
			return new ApiResponse<EnglishDTO>(false, "Question not found", null, "NOT_FOUND", LocalDateTime.now());
		}

	}

	@GetMapping("/getAllQuestions")
	public List<EnglishDTO> getAllQuestions() {
		return service.getAllQuestions();
	}

	@PostMapping("/addCategory")
	public ApiResponse<EnglishCategory> insertQuestion(@RequestBody EnglishCategory category) {
		try {
			EnglishCategory addedCategory = service.insertEnglishCategory(category);
			return new ApiResponse<EnglishCategory>(true, "Category added", addedCategory, LocalDateTime.now());
		} catch (DataIntegrityViolationException de) {
			return new ApiResponse<EnglishCategory>(true, "Category already exists", null, LocalDateTime.now());
		} catch (Exception e) {
			return new ApiResponse<EnglishCategory>(true, "An error occurred while saving the category", null,
					LocalDateTime.now());
		}

	}

	@GetMapping("/getCategories")
	public List<EnglishCategory> getCategories() {
		return service.getCategories();
	}

}
