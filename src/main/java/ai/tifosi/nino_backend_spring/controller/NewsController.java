package ai.tifosi.nino_backend_spring.controller;

import ai.tifosi.nino_backend_spring.model.News;
import ai.tifosi.nino_backend_spring.repository.NewsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {
    private final NewsRepository newsRepository;

    public NewsController(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @GetMapping
    public ResponseEntity<List<News>> getAllNews() {
        List<News> news = newsRepository.findAllByOrderByPublishedAtDesc();

        return ResponseEntity.ok(news);
    }
}
