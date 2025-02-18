package ai.tifosi.nino_backend_spring.dto.news.sourceDto;

import ai.tifosi.nino_backend_spring.dto.news.platformDto.PlatformDto;

import java.io.Serializable;


public record SourceDto(Long id, String link, PlatformDto platform, String name, int activity) implements Serializable {

}