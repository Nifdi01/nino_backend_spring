package ai.tifosi.nino_backend_spring.controller.news;

import ai.tifosi.nino_backend_spring.dto.news.platformDto.GetPlatformDto;
import ai.tifosi.nino_backend_spring.dto.news.platformDto.PostPlatformDto;
import ai.tifosi.nino_backend_spring.model.news.Platform;
import ai.tifosi.nino_backend_spring.service.news.PlatformService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/platforms/")
public class PlatformController {
    private final PlatformService platformService;

    public PlatformController(PlatformService platformService) {
        this.platformService = platformService;
    }

    @GetMapping
    public ResponseEntity<List<GetPlatformDto>> getAllPlatforms() {
        return ResponseEntity.ok(platformService.getAllPlatforms());
    }

    @PostMapping
    public ResponseEntity<Platform> createPlatform(@RequestBody PostPlatformDto request){
        try {
            Platform platform = platformService.createPlatform(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(platform);
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }
}
