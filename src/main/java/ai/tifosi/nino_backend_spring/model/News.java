package ai.tifosi.nino_backend_spring.model;

import ai.tifosi.nino_backend_spring.model.Keyword;
import ai.tifosi.nino_backend_spring.model.Source;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="news_news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false)
    private String title;

    @Column(length = 2048, unique = true, nullable = false)
    private String link;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_id", nullable = false)
    private Source source;

    @ManyToMany
    @JoinTable(
            name = "news_news_keywords",
            joinColumns = @JoinColumn(name="news_id"),
            inverseJoinColumns = @JoinColumn(name = "keywords_id")
    )
    private Set<Keyword> keywords;

    @Column(name="published_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime publishedAt;

    @Column(nullable = false)
    private int viewed=0;

    public News() {
    }

    public News(String title, String link, String content, Source source, Set<Keyword> keywords) {
        this.title = title;
        this.link = link;
        this.content = content;
        this.source = source;
        this.keywords = keywords;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public Set<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(Set<Keyword> keywords) {
        this.keywords = keywords;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }

    public int getViewed() {
        return viewed;
    }

    public void setViewed(int viewed) {
        this.viewed = viewed;
    }

    @Override
    public String toString() {
        return title;
    }
}