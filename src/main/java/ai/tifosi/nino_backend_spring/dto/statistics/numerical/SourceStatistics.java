package ai.tifosi.nino_backend_spring.dto.statistics.numerical;

import java.io.Serializable;

public record SourceStatistics(long sourceCount, String mostFrequentSourceName, String leastFrequentSourceName, String mostFrequentPlatform) implements Serializable {
}