package ai.tifosi.nino_backend_spring.service.news;

import ai.tifosi.nino_backend_spring.dto.news.platformDto.PlatformDto;
import ai.tifosi.nino_backend_spring.model.news.Platform;
import ai.tifosi.nino_backend_spring.repository.news.PlatformRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlatformService {
    private final PlatformRepository platformRepository;

    public PlatformService(PlatformRepository platformRepository) {
        this.platformRepository = platformRepository;
    }

    public List<PlatformDto> getAllPlatforms() {
        List<Platform> platforms = platformRepository.findAll();
        return platforms.stream()
                .map(platform -> new PlatformDto(platform.getId(), platform.getName(), platform.getFrequency()))
                .collect(Collectors.toList());
    }
}
