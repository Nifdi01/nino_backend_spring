package ai.tifosi.nino_backend_spring.dto.news.newsDto;

import ai.tifosi.nino_backend_spring.dto.news.keywordDto.GetKeywordDto;
import ai.tifosi.nino_backend_spring.dto.news.sourceDto.GetSourceDto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;


public record NewsDto(Long id, String title, String link, String content, GetSourceDto source, Set<GetKeywordDto> keywords,
                      LocalDateTime publishedAt, int viewed) implements Serializable {

}