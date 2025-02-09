package ai.tifosi.nino_backend_spring.repository.news;

import ai.tifosi.nino_backend_spring.model.news.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceRepository extends JpaRepository<Source, Long> {
}