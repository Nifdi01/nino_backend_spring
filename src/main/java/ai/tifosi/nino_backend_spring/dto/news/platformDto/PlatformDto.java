package ai.tifosi.nino_backend_spring.dto.news.platformDto;

import ai.tifosi.nino_backend_spring.model.news.Platform;

import java.io.Serializable;

public record PlatformDto(String name) implements Serializable {
    public PlatformDto(Platform platform) {
        this(
                platform != null ? platform.getName() : null
        );
    }
}
