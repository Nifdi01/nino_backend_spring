package ai.tifosi.nino_backend_spring.dto.news.newsDto;

public record NewsRequest(int page, int pageSize, String search, NewsFilters filters) {
}
