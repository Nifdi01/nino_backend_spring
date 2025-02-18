package ai.tifosi.nino_backend_spring.mapper;

import ai.tifosi.nino_backend_spring.dto.news.platformDto.PlatformDto;
import ai.tifosi.nino_backend_spring.model.news.Platform;

public class PlatformMapper {
    public static PlatformDto toDto(Platform platform) {
        return new PlatformDto(
                platform.getId(),
                platform.getName(),
                platform.getFrequency()
        );
    }
}
