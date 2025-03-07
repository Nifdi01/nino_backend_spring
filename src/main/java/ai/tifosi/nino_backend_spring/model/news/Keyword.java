package ai.tifosi.nino_backend_spring.model.news;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "keywords")
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private int frequency = 0;

    @ManyToMany(mappedBy = "keywords")
    private Set<News> news = new HashSet<>();

    public Keyword() {
    }

    public Keyword(String name) {
        this.name = name;
    }
}
