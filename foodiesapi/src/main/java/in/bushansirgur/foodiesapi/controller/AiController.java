package in.bushansirgur.foodiesapi.controller;

import in.bushansirgur.foodiesapi.io.AiSuggestionResponse;
import in.bushansirgur.foodiesapi.io.RecipeDTO;
import in.bushansirgur.foodiesapi.service.AiGenerationService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@AllArgsConstructor
public class AiController {

    private final AiGenerationService aiGenerationService;

    // This is a simple Data Transfer Object (DTO) for the request body
    @Data
    static class GenerateRequest {
        private String foodName;
        private String category;
    }

    @PostMapping("/generate-suggestions")
    public ResponseEntity<AiSuggestionResponse> generateSuggestions(@RequestBody GenerateRequest request) {
        AiSuggestionResponse response = aiGenerationService.generateSuggestions(request.getFoodName(), request.getCategory());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/recipe")
    public ResponseEntity<RecipeDTO> generateRecipe(@RequestParam String dishName) {
        if (dishName == null || dishName.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        RecipeDTO recipe = aiGenerationService.generateRecipe(dishName);
        if (recipe != null) {
            return ResponseEntity.ok(recipe);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}