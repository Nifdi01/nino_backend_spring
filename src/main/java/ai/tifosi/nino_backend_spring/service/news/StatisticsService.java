package ai.tifosi.nino_backend_spring.service.news;

import ai.tifosi.nino_backend_spring.dto.news.keywordDto.KeywordDto;
import ai.tifosi.nino_backend_spring.dto.news.platformDto.PlatformDto;
import ai.tifosi.nino_backend_spring.dto.news.sourceDto.SourceDto;
import ai.tifosi.nino_backend_spring.dto.statistics.KeywordStatistics;
import ai.tifosi.nino_backend_spring.dto.statistics.NewsStatistics;
import ai.tifosi.nino_backend_spring.dto.statistics.OverviewStatistics;
import ai.tifosi.nino_backend_spring.dto.statistics.SourceStatistics;
import ai.tifosi.nino_backend_spring.model.news.Keyword;
import ai.tifosi.nino_backend_spring.model.news.Platform;
import ai.tifosi.nino_backend_spring.model.news.Source;
import ai.tifosi.nino_backend_spring.repository.news.KeywordRepository;
import ai.tifosi.nino_backend_spring.repository.news.NewsRepository;
import ai.tifosi.nino_backend_spring.repository.news.PlatformRepository;
import ai.tifosi.nino_backend_spring.repository.news.SourceRepository;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {
    private final SourceRepository sourceRepository;
    private final KeywordRepository keywordRepository;
    private final NewsRepository newsRepository;
    private final PlatformRepository platformRepository;

    public StatisticsService(SourceRepository sourceRepository, KeywordRepository keywordRepository, NewsRepository newsRepository, PlatformRepository platformRepository) {
        this.sourceRepository = sourceRepository;
        this.keywordRepository = keywordRepository;
        this.newsRepository = newsRepository;
        this.platformRepository = platformRepository;
    }

    public OverviewStatistics getOverviewStatistics(){
        long newsCount = newsRepository.count();
        long sourceCount = sourceRepository.count();
        Source mostFrequentSource = sourceRepository.findTopByOrderByFrequencyDesc().orElse(null);
        Keyword mostFrequentKeyword = keywordRepository.findTopByOrderByFrequencyDesc().orElse(null);

        return new OverviewStatistics(
                newsCount,
                sourceCount,
                new SourceDto(mostFrequentSource).name(),
                new KeywordDto(mostFrequentKeyword).name()
        );
    }

    public SourceStatistics getSourceStatistics(){
        long sourceCount = sourceRepository.count();
        Source mostFrequentSource = sourceRepository.findTopByOrderByFrequencyDesc().orElse(null);
        Source leastFrequentSource = sourceRepository.findTopByOrderByFrequency().orElse(null);
        Platform mostFrequentPlatform = platformRepository.findTopByOrderByFrequencyDesc().orElse(null);

        return new SourceStatistics(
                sourceCount,
                new SourceDto(mostFrequentSource).name(),
                new SourceDto(leastFrequentSource).name(),
                new PlatformDto(mostFrequentPlatform).name()
        );
    }

    public NewsStatistics getNewsStatistics(){
        long newsCount = newsRepository.count();
        Source mostFrequentSource = sourceRepository.findTopByOrderByFrequencyDesc().orElse(null);
        Source leastFrequentSource = sourceRepository.findTopByOrderByFrequency().orElse(null);
        Platform mostFrequentPlatform = platformRepository.findTopByOrderByFrequencyDesc().orElse(null);

        return new NewsStatistics(
                newsCount,
                new SourceDto(mostFrequentSource).name(),
                new SourceDto(leastFrequentSource).name(),
                new PlatformDto(mostFrequentPlatform).name()
        );
    }

    public KeywordStatistics getKeywordStatistics(){
        long keywordCount = keywordRepository.count();
        Keyword mostFrequentKeyword = keywordRepository.findTopByOrderByFrequencyDesc().orElse(null);
        Keyword leastFrequentKeyword = keywordRepository.findTopByOrderByFrequency().orElse(null);

        return new KeywordStatistics(
                keywordCount,
                new KeywordDto(mostFrequentKeyword).name(),
                new KeywordDto(leastFrequentKeyword).name()
        );
    }
}
