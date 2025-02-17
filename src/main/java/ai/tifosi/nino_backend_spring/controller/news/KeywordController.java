package ai.tifosi.nino_backend_spring.controller.news;

import ai.tifosi.nino_backend_spring.dto.news.KeywordDto;
import ai.tifosi.nino_backend_spring.service.news.KeywordService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/keywords/")
public class KeywordController {
    private final KeywordService keywordService;

    public KeywordController(KeywordService keywordService) {
        this.keywordService = keywordService;
    }

    @GetMapping
    public ResponseEntity<Page<KeywordDto>> getKeywords(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "20") int pageSize,
            @RequestParam(name = "search", defaultValue = "") String search) {

        // Set up pagination with the given page and pageSize parameters
        Pageable pageable = PageRequest.of(page - 1, pageSize);

        // Fetch the paginated results
        Page<KeywordDto> keywordPage = keywordService.getAllKeywords(search, pageable);

        // Return paginated results
        return ResponseEntity.ok(keywordPage);
    }
}