package ai.tifosi.nino_backend_spring.dto.news.newsDto;

import java.util.List;

public record NewsFilters(List<String> sources, List<String> platforms, List<String> keywords, String endDate, String startDate) {
}
