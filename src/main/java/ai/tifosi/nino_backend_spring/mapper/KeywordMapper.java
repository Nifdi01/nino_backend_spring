package ai.tifosi.nino_backend_spring.mapper;

import ai.tifosi.nino_backend_spring.dto.news.keywordDto.GetKeywordDto;
import ai.tifosi.nino_backend_spring.model.news.Keyword;

public class KeywordMapper {
    public static GetKeywordDto toDto(Keyword keyword) {
        return new GetKeywordDto(
                keyword.getId(),
                keyword.getName(),
                keyword.getFrequency()
        );
    }
}
