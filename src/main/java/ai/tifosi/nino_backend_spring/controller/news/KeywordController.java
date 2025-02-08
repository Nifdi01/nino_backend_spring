package ai.tifosi.nino_backend_spring.controller.news;

import ai.tifosi.nino_backend_spring.dto.news.KeywordDto;
import ai.tifosi.nino_backend_spring.service.news.KeywordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/keywords")  // Base path for keywords API
public class KeywordController {
    private final KeywordService keywordService;

    public KeywordController(KeywordService keywordService) {
        this.keywordService = keywordService;
    }

    @GetMapping
    public ResponseEntity<List<KeywordDto>> getAllKeywords() {
        return ResponseEntity.ok(keywordService.getAllKeywords());
    }
}