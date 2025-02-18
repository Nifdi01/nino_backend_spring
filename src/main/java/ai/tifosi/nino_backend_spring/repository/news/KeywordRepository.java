package ai.tifosi.nino_backend_spring.repository.news;

import ai.tifosi.nino_backend_spring.model.news.Keyword;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    Page<Keyword> findByNameContaining(String search, Pageable pageable);

    Optional<Keyword> findByName(String name);
}