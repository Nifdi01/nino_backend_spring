package ai.tifosi.nino_backend_spring.dto.statistics;

import ai.tifosi.nino_backend_spring.model.news.Keyword;
import ai.tifosi.nino_backend_spring.model.news.Source;

import java.io.Serializable;

public record OverviewStatistics(long newsCount, long sourceCount, Source mostFrequentSource, Keyword mostFrequentKeywordName) implements Serializable {
}
