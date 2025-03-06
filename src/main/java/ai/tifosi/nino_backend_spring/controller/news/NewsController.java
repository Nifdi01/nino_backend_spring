package ai.tifosi.nino_backend_spring.controller.news;

import ai.tifosi.nino_backend_spring.dto.news.newsDto.NewsRequest;
import ai.tifosi.nino_backend_spring.service.news.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/news/")
public class NewsController {
    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> getAllNews(@RequestBody NewsRequest request){
        System.out.println(request);
        int page = request.page() - 1;
        int pageSize = request.pageSize();

        Map<String, Object> response = newsService.getAllNews(page, pageSize, request.search(), request.filters());
        return ResponseEntity.ok(response);
    }
}
