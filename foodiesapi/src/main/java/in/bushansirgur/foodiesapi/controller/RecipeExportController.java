//package in.bushansirgur.foodiesapi.controller;
//
//import in.bushansirgur.foodiesapi.io.RecipeDTO;
//import in.bushansirgur.foodiesapi.service.AiGenerationService;
//import in.bushansirgur.foodiesapi.service.PdfService;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.PostMapping; // Make sure to import PostMapping
//import org.springframework.web.bind.annotation.RequestBody; // And RequestBody
//import java.io.IOException;
//
//@RestController
//@RequestMapping("/api/export")
//@AllArgsConstructor
//public class RecipeExportController {
//
//    private final PdfService pdfService;
//    // No longer need AiGenerationService here
//    // private final AiGenerationService aiGenerationService;
//
//    @PostMapping("/recipe/pdf") // Changed from GetMapping to PostMapping
//    public ResponseEntity<byte[]> downloadRecipePdf(@RequestParam String dishName, @RequestBody RecipeDTO recipe) throws IOException {
//        if (dishName == null || dishName.trim().isEmpty() || recipe == null) {
//            return ResponseEntity.badRequest().build();
//        }
//
//        // The recipe is now passed directly, so we don't need to generate it again.
//        byte[] pdfBytes = pdfService.generateRecipePdf(dishName, recipe);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_PDF);
//        String filename = dishName.replaceAll("\\s+", "_") + "_recipe.pdf";
//        headers.setContentDispositionFormData("attachment", filename);
//        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
//
//        return ResponseEntity.ok().headers(headers).body(pdfBytes);
//    }
//}