package ai.tifosi.nino_backend_spring.dto.news.platformDto;

import java.io.Serializable;


public record PlatformDto(Long id, String name, int frequency) implements Serializable {

}