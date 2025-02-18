package ai.tifosi.nino_backend_spring.dto.news.keywordDto;

import java.io.Serializable;

public record GetKeywordDto(Long id, String name, int frequency) implements Serializable {
}