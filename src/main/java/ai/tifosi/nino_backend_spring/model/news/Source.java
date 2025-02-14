package ai.tifosi.nino_backend_spring.model.news;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sources")
public class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String link;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platform_id", nullable = false)
    private Platform platform;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false)
    private int activity = 0;

    public Source() {
    }

    public Source(String link, Platform platform, String name, int activity) {
        this.link = link;
        this.platform = platform;
        this.name = name;
        this.activity = activity;
    }
    
}