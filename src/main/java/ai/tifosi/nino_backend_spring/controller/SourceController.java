package ai.tifosi.nino_backend_spring.controller;

import ai.tifosi.nino_backend_spring.model.Source;
import ai.tifosi.nino_backend_spring.repository.SourceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sources")
public class SourceController {
    private final SourceRepository sourceRepository;

    public SourceController(SourceRepository sourceRepository) {
        this.sourceRepository = sourceRepository;
    }

    @GetMapping
    public ResponseEntity<List<Source>> getAllSources() {
        List<Source> sources = sourceRepository.findAll();
        return ResponseEntity.ok(sources);
    }
}
