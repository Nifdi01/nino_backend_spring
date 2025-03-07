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
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class NewsService {
    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public Map<String, Object> getAllNews(int page, int pageSize, String search, NewsFilters filters) {
        // Adjust to zero-based page indexing
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("publishedAt").descending());

        // For the start date: beginning of yesterday
        LocalDateTime startDate = filters.startDate() != null
                ? OffsetDateTime.parse(filters.startDate()).atZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime().with(LocalTime.MIN)
                : LocalDateTime.now().minusDays(1).with(LocalTime.MIN);
        // For the end date: end of today
        LocalDateTime endDate = filters.endDate() != null
                ? OffsetDateTime.parse(filters.endDate()).atZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime().with(LocalTime.MAX)
                : LocalDateTime.now().with(LocalTime.MAX);


        // Handle keywords with case-insensitivity
        List<String> keywordNames = filters.keywords().isEmpty() ? null : filters.keywords().stream().map(String::toLowerCase).collect(Collectors.toList());

        Page<News> newsPage = newsRepository.findByFilters(
                search != null ? search : "",
                filters.sources().isEmpty() ? null : filters.sources(),
                filters.platforms().isEmpty() ? null : filters.platforms(),
                keywordNames,
                startDate,
                endDate,
                pageable
        );

        Map<String, Object> response = new HashMap<>();
        response.put("results", newsPage.getContent().stream().map(NewsMapper::toDto).collect(Collectors.toList()));
        response.put("next", newsPage.hasNext() ? page + 1 : null); // Correct next page for one-based indexing
        response.put("count", newsPage.getTotalElements());

        return response;
    }
}