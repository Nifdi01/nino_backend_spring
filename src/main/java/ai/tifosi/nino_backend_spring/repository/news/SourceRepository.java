package ai.tifosi.nino_backend_spring.repository.news;

import ai.tifosi.nino_backend_spring.model.news.Source;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SourceRepository extends JpaRepository<Source, Long> {
    Page<Source> findByNameContaining(String search, Pageable pageable);

    Optional<Source> findByLink(String link);

    Optional<Source> findTopByOrderByFrequencyDesc();

    Optional<Source> findTopByOrderByFrequency();
}