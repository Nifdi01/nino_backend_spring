package ai.tifosi.nino_backend_spring.controller.news;

import ai.tifosi.nino_backend_spring.dto.news.SourceDto;
import ai.tifosi.nino_backend_spring.service.news.SourceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sources")
public class SourceController {
    private final SourceService sourceService;

    public SourceController(SourceService sourceService) {
        this.sourceService = sourceService;
    }

    @GetMapping
    public ResponseEntity<List<SourceDto>> getAllSources() {
        return ResponseEntity.ok(sourceService.getAllSources());
    }
}
