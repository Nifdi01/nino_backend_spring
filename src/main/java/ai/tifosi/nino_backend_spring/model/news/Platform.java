package ai.tifosi.nino_backend_spring.model.news;

import jakarta.persistence.*;

@Entity
@Table(name = "news_platforms")
public class Platform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private int frequency = 0;

    public Platform() {
    }

    public Platform(String name, int frequency) {
        this.name = name;
        this.frequency = frequency;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return name;
    }
}