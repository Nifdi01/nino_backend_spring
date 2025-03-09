package ai.tifosi.nino_backend_spring.service.statistics;

import ai.tifosi.nino_backend_spring.dto.statistics.visual.KeywordDashboard;
import ai.tifosi.nino_backend_spring.dto.statistics.visual.SourceDashboard;
import ai.tifosi.nino_backend_spring.model.news.Keyword;
import ai.tifosi.nino_backend_spring.model.news.Source;
import ai.tifosi.nino_backend_spring.repository.news.KeywordRepository;
import ai.tifosi.nino_backend_spring.repository.news.NewsRepository;
import ai.tifosi.nino_backend_spring.repository.news.SourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisualStatisticsService {
    private final NewsRepository newsRepository;
    private final KeywordRepository keywordRepository;
    private final SourceRepository sourceRepository;

    public VisualStatisticsService(NewsRepository newsRepository, KeywordRepository keywordRepository, SourceRepository sourceRepository) {
        this.newsRepository = newsRepository;
        this.keywordRepository = keywordRepository;
        this.sourceRepository = sourceRepository;
    }

    public List<SourceDashboard> getSourceDashboard() {
        List<Source> topFiveSources = sourceRepository.findTop5ByOrderByFrequencyDesc();

        return topFiveSources.stream().map(source -> new SourceDashboard(source.getName(), source.getFrequency())).toList();
    }

    public List<KeywordDashboard> getKeywordDashboard(){
        List<Keyword> topFiveKeywords = keywordRepository.findTop5ByOrderByFrequencyDesc();

        return topFiveKeywords.stream().map(keyword -> new KeywordDashboard(keyword.getName(), keyword.getFrequency())).toList();
    }
}
