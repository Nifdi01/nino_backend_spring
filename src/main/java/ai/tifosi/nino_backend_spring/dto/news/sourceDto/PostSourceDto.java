package ai.tifosi.nino_backend_spring.dto.news.sourceDto;

import ai.tifosi.nino_backend_spring.dto.news.platformDto.GetPlatformDto;

import java.io.Serializable;

public record PostSourceDto(String link, Long platform, String name) implements Serializable {
}