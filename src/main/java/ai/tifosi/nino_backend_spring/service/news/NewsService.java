package ai.tifosi.nino_backend_spring.service.news;

import ai.tifosi.nino_backend_spring.dto.news.NewsDto;
import ai.tifosi.nino_backend_spring.mapper.NewsMapper;
import ai.tifosi.nino_backend_spring.model.news.News;
import ai.tifosi.nino_backend_spring.repository.news.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsService {
    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<NewsDto> getAllNews() {
        List<News> newsList = newsRepository.findAllByOrderByPublishedAtDesc();
        return newsList.stream().map(NewsMapper::toDto).collect(Collectors.toList());
    }
}
