package ai.tifosi.nino_backend_spring.mapper;

import ai.tifosi.nino_backend_spring.dto.news.platformDto.GetPlatformDto;
import ai.tifosi.nino_backend_spring.model.news.Platform;

public class PlatformMapper {
    public static GetPlatformDto toDto(Platform platform) {
        return new GetPlatformDto(
                platform.getId(),
                platform.getName(),
                platform.getFrequency()
        );
    }
}
