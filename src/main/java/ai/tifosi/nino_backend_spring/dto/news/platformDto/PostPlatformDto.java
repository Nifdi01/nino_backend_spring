package ai.tifosi.nino_backend_spring.dto.news.platformDto;

import java.io.Serializable;

/**
 * DTO for {@link ai.tifosi.nino_backend_spring.model.news.Platform}
 */
public record PostPlatformDto(String name) implements Serializable {
}