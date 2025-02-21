package ai.tifosi.nino_backend_spring.dto.news.sourceDto;

import ai.tifosi.nino_backend_spring.dto.news.platformDto.GetPlatformDto;

import java.io.Serializable;


public record GetSourceDto(Long id, String link, GetPlatformDto platform, String name, int activity) implements Serializable {

}