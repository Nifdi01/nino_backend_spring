package ai.tifosi.nino_backend_spring.dto.news;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO for {@link ai.tifosi.nino_backend_spring.model.news.News}
 */
public class NewsDto implements Serializable {
    private final Long id;
    private final String title;
    private final String link;
    private final String content;
    private final SourceDto source;
    private final Set<KeywordDto> keywords;
    private final LocalDateTime publishedAt;
    private final int viewed;

    public NewsDto(Long id, String title, String link, String content, SourceDto source, Set<KeywordDto> keywords, LocalDateTime publishedAt, int viewed) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.content = content;
        this.source = source;
        this.keywords = keywords;
        this.publishedAt = publishedAt;
        this.viewed = viewed;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getContent() {
        return content;
    }

    public SourceDto getSource() {
        return source;
    }

    public Set<KeywordDto> getKeywords() {
        return keywords;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public int getViewed() {
        return viewed;
    }
    
}