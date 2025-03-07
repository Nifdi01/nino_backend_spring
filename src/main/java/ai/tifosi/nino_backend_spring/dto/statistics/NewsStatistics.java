package ai.tifosi.nino_backend_spring.dto.statistics;


import java.io.Serializable;

public record NewsStatistics(long newsCount, long sourceCount, String mostFrequentSource, String mostFrequentKeywordName) implements Serializable {
}
