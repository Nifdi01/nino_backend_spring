package ai.tifosi.nino_backend_spring.service.news;

import ai.tifosi.nino_backend_spring.dto.statistics.OverviewStatistics;
import ai.tifosi.nino_backend_spring.model.news.Keyword;
import ai.tifosi.nino_backend_spring.model.news.Source;
import ai.tifosi.nino_backend_spring.repository.news.KeywordRepository;
import ai.tifosi.nino_backend_spring.repository.news.NewsRepository;
import ai.tifosi.nino_backend_spring.repository.news.SourceRepository;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {
    private final SourceRepository sourceRepository;
    private final KeywordRepository keywordRepository;
    private final NewsRepository newsRepository;

    public StatisticsService(SourceRepository sourceRepository, KeywordRepository keywordRepository, NewsRepository newsRepository) {
        this.sourceRepository = sourceRepository;
        this.keywordRepository = keywordRepository;
        this.newsRepository = newsRepository;
    }

    public OverviewStatistics getOverviewStatistics(){
        long newsCount = newsRepository.count();
        long sourceCount = sourceRepository.count();
        Source mostFrequentSource = sourceRepository.findTopByOrderByFrequencyDesc().orElse(null);
        Keyword mostFrequentKeyword = keywordRepository.findTopByOrderByFrequencyDesc().orElse(null);

        return new OverviewStatistics(
                newsCount,
                sourceCount,
                mostFrequentSource,
                mostFrequentKeyword
        );
    }
}
