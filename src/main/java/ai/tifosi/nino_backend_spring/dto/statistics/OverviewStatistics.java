package ai.tifosi.nino_backend_spring.dto.statistics;

import java.io.Serializable;

public record OverviewStatistics(long newsCount, long sourceCount, String mostFrequentSourceName, String mostFrequentKeywordName) implements Serializable {
}
