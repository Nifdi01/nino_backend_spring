package ai.tifosi.nino_backend_spring.model.news;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 2048, unique = true, nullable = false)
    private String link;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_id", nullable = false)
    private Source source;

    @ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "news_keywords",
            joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "keywords_id")
    )
    private Set<Keyword> keywords = new HashSet<>();

    @Column(name = "published_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime publishedAt;

    @Column(nullable = false)
    private int viewed = 0;

    public News() {
    }
}