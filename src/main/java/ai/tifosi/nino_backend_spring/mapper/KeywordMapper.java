package ai.tifosi.nino_backend_spring.mapper;

import ai.tifosi.nino_backend_spring.dto.news.KeywordDto;
import ai.tifosi.nino_backend_spring.model.news.Keyword;

public class KeywordMapper {
    public static KeywordDto toDto(Keyword keyword) {
        return new KeywordDto(
                keyword.getId(),
                keyword.getName(),
                keyword.getFrequency()
        );
    }
}
