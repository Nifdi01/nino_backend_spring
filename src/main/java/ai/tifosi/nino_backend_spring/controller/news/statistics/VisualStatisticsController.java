package ai.tifosi.nino_backend_spring.controller.news.statistics;

import ai.tifosi.nino_backend_spring.dto.statistics.visual.KeywordDashboard;
import ai.tifosi.nino_backend_spring.dto.statistics.visual.SourceDashboard;
import ai.tifosi.nino_backend_spring.service.statistics.VisualStatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(("/api/dashboard"))
public class VisualStatisticsController {
    private final VisualStatisticsService visualStatisticsService;

    public VisualStatisticsController(VisualStatisticsService visualStatisticsService) {
        this.visualStatisticsService = visualStatisticsService;
    }

    @GetMapping("/sources")
    public ResponseEntity<List<SourceDashboard>> getSources(){
        List<SourceDashboard> sources = visualStatisticsService.getSourceDashboard();
        return ResponseEntity.ok(sources);
    }

    @GetMapping("/keywords")
    public ResponseEntity<List<KeywordDashboard>> getKeywords(){
        List<KeywordDashboard> keywords = visualStatisticsService.getKeywordDashboard();
        return ResponseEntity.ok(keywords);
    }
}
