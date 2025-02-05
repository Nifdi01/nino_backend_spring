package ai.tifosi.nino_backend_spring.repository;

import ai.tifosi.nino_backend_spring.model.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
}