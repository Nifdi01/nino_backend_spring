package ai.tifosi.nino_backend_spring.repository.news;

import ai.tifosi.nino_backend_spring.model.news.News;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    @EntityGraph(attributePaths = {"source", "source.platform", "keywords"})
    List<News> findAllByOrderByPublishedAtDesc();
}