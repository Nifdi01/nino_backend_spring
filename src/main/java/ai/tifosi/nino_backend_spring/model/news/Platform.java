package ai.tifosi.nino_backend_spring.model.news;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "platforms")
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

    public Platform(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}