package ai.tifosi.nino_backend_spring.mapper;

import ai.tifosi.nino_backend_spring.dto.news.newsDto.NewsDto;
import ai.tifosi.nino_backend_spring.model.news.News;

import java.util.stream.Collectors;

public class NewsMapper {
    public static NewsDto toDto(News news) {
        return new NewsDto(
                news.getId(),
                news.getTitle(),
                news.getLink(),
                news.getContent(),
                SourceMapper.toDto(news.getSource()),
                news.getKeywords().stream().map(KeywordMapper::toDto).collect(Collectors.toSet()),
                news.getPublishedAt(),
                news.getViewed()
        );
    }
}
