package ai.tifosi.nino_backend_spring.controller.news;

import ai.tifosi.nino_backend_spring.dto.news.keywordDto.GetKeywordDto;
import ai.tifosi.nino_backend_spring.dto.news.keywordDto.PostKeywordDto;
import ai.tifosi.nino_backend_spring.service.news.KeywordService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/keywords/")
public class KeywordController {
    private final KeywordService keywordService;

    public KeywordController(KeywordService keywordService) {
        this.keywordService = keywordService;
    }

    @GetMapping
    public ResponseEntity<Page<GetKeywordDto>> getAllKeywords(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "20") int pageSize,
            @RequestParam(name = "search", defaultValue = "") String search) {

        // Set up pagination with the given page and pageSize parameters
        Pageable pageable = PageRequest.of(page - 1, pageSize);

        // Fetch the paginated results
        Page<GetKeywordDto> keywordPage = keywordService.getAllKeywords(search, pageable);

        // Return paginated results
        return ResponseEntity.ok(keywordPage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteKeyword(@PathVariable Long id) {
        keywordService.deleteKeyword(id);
        return ResponseEntity.status(HttpStatus.OK).body("Keyword deleted successfully");
    }

    @PostMapping
    public ResponseEntity<String> createKeyword(
            @RequestBody PostKeywordDto request
    ) {
        keywordService.createKeyword(request);

        return ResponseEntity.status(HttpStatus.CREATED).body("Keyword Created successfully");
    }
}