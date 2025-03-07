package ai.tifosi.nino_backend_spring.dto.statistics;


import java.io.Serializable;

public record NewsStatistics(long newsCount, String mostFrequentSourceName, String leastFrequentSourceName, String mostFrequentPlatformName) implements Serializable {
}
