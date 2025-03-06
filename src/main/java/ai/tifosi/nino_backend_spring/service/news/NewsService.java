package ai.tifosi.nino_backend_spring.service.news;

import ai.tifosi.nino_backend_spring.dto.news.newsDto.NewsFilters;
import ai.tifosi.nino_backend_spring.mapper.NewsMapper;
import ai.tifosi.nino_backend_spring.model.news.News;
import ai.tifosi.nino_backend_spring.repository.news.NewsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class NewsService {
    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public Map<String, Object> getAllNews(int page, int pageSize, String search, NewsFilters filters){
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("publishedAt").descending());

        LocalDateTime startDate = filters.startDate() != null
                ? OffsetDateTime.parse(filters.startDate()).atZoneSameInstant(java.time.ZoneId.systemDefault()).toLocalDateTime()
                : LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = filters.endDate() != null
                ? OffsetDateTime.parse(filters.endDate()).atZoneSameInstant(java.time.ZoneId.systemDefault()).toLocalDateTime()
                : LocalDateTime.now();

        Page<News> newsPage = newsRepository.findByFilters(
                search != null ? search : "",
                filters.sources().isEmpty() ? null : filters.sources(),
                filters.keywords().isEmpty() ? null : filters.keywords(),
                filters.platforms().isEmpty() ? null : filters.platforms(),
                startDate,
                endDate,
                pageable
        );

        Map<String, Object> response = new HashMap<>();
        response.put("results", newsPage.getContent().stream().map(NewsMapper::toDto).collect(Collectors.toList()));
        response.put("next", newsPage.hasNext() ? page + 2 : null);
        response.put("count", newsPage.getTotalElements());

        return response;
    }
}
