package ai.tifosi.nino_backend_spring.controller;

import ai.tifosi.nino_backend_spring.model.Platform;
import ai.tifosi.nino_backend_spring.repository.PlatformRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/platforms")
public class PlatformController {
    private final PlatformRepository platformRepository;

    public PlatformController(PlatformRepository platformRepository) {
        this.platformRepository = platformRepository;
    }

    @GetMapping
    public ResponseEntity<List<Platform>> getAllPlatforms() {
        List<Platform> platforms = platformRepository.findAll();
        return ResponseEntity.ok(platforms);
    }
}
