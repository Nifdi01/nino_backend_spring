package ai.tifosi.nino_backend_spring.dto.statistics.numerical;

import java.io.Serializable;

public record KeywordStatistics(long keywordCount, String mostFrequentKeywordName, String leastFrequentKeywordName) implements Serializable{

}
