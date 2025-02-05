package ai.tifosi.nino_backend_spring.repository;

import ai.tifosi.nino_backend_spring.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}