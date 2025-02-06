package ai.tifosi.nino_backend_spring.controller;

import ai.tifosi.nino_backend_spring.model.Keyword;
import ai.tifosi.nino_backend_spring.repository.KeywordRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/keywords")  // Base path for keywords API
public class KeywordController {

    private final KeywordRepository keywordRepository;

    public KeywordController(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }

    @GetMapping
    public ResponseEntity<List<Keyword>> getAllKeywords() {
        List<Keyword> keywords = keywordRepository.findAll();
        System.out.println("Fetched keywords: " + keywords);  // Debugging line
        return ResponseEntity.ok(keywords);
    }
}