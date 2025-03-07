package ai.tifosi.nino_backend_spring.controller.news;

import ai.tifosi.nino_backend_spring.dto.statistics.OverviewStatistics;
import ai.tifosi.nino_backend_spring.service.news.StatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {
    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService){
        this.statisticsService = statisticsService;
    }

    @GetMapping("/overview")
    public ResponseEntity<OverviewStatistics> GetOverviewStatistics(){
        return ResponseEntity.ok(statisticsService.getOverviewStatistics());
    }
}
