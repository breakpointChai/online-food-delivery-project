package in.bushansirgur.foodiesapi.controller;

import in.bushansirgur.foodiesapi.io.AiSuggestionResponse;
import in.bushansirgur.foodiesapi.service.AiGenerationService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}