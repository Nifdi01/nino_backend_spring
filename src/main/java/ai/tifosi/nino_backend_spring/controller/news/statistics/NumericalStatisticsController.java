package ai.tifosi.nino_backend_spring.controller.news.statistics;

import ai.tifosi.nino_backend_spring.dto.statistics.numerical.KeywordStatistics;
import ai.tifosi.nino_backend_spring.dto.statistics.numerical.NewsStatistics;
import ai.tifosi.nino_backend_spring.dto.statistics.numerical.OverviewStatistics;
import ai.tifosi.nino_backend_spring.dto.statistics.numerical.SourceStatistics;
import ai.tifosi.nino_backend_spring.service.statistics.NumericalStatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics")
public class NumericalStatisticsController {
    private final NumericalStatisticsService numericalStatisticsService;

    public NumericalStatisticsController(NumericalStatisticsService numericalStatisticsService){
        this.numericalStatisticsService = numericalStatisticsService;
    }

    @GetMapping("/overview")
    public ResponseEntity<OverviewStatistics> GetOverviewStatistics(){
        return ResponseEntity.ok(numericalStatisticsService.getOverviewStatistics());
    }

    @GetMapping("/sources")
    public ResponseEntity<SourceStatistics> getSourceStatistics(){
        return ResponseEntity.ok(numericalStatisticsService.getSourceStatistics());
    }

    @GetMapping("/news")
    public ResponseEntity<NewsStatistics> getNewsStatistics(){
        return ResponseEntity.ok(numericalStatisticsService.getNewsStatistics());
    }

    @GetMapping("/keywords")
    public ResponseEntity<KeywordStatistics> getKeywordStatistics(){
        return ResponseEntity.ok(numericalStatisticsService.getKeywordStatistics());
    }

}
