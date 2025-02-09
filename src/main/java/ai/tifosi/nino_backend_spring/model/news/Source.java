package ai.tifosi.nino_backend_spring.model.news;

import jakarta.persistence.*;

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

    public Long getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return name;
    }
}