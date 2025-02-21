package ai.tifosi.nino_backend_spring.service.news;

import ai.tifosi.nino_backend_spring.dto.news.platformDto.GetPlatformDto;
import ai.tifosi.nino_backend_spring.dto.news.platformDto.PostPlatformDto;
import ai.tifosi.nino_backend_spring.model.news.Platform;
import ai.tifosi.nino_backend_spring.repository.news.PlatformRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlatformService {
    private final PlatformRepository platformRepository;

    public PlatformService(PlatformRepository platformRepository) {
        this.platformRepository = platformRepository;
    }

    public List<GetPlatformDto> getAllPlatforms() {
        List<Platform> platforms = platformRepository.findAll();
        return platforms.stream()
                .map(platform -> new GetPlatformDto(platform.getId(), platform.getName(), platform.getFrequency()))
                .collect(Collectors.toList());
    }

    public Platform createPlatform(PostPlatformDto request) {
        Optional<Platform> existingPlatform = platformRepository.findByName(request.name());

        if(existingPlatform.isPresent()){
            throw new IllegalArgumentException("Platform already exists: "+ request.name());
        }

        Platform platform = new Platform(request.name());
        platformRepository.save(platform);
        return platform;
    }
}
