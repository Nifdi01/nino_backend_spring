package ai.tifosi.nino_backend_spring.repository.news;

import ai.tifosi.nino_backend_spring.model.news.Keyword;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    Page<Keyword> findByNameContaining(String search, Pageable pageable);

    Optional<Keyword> findByName(String name);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM news_keywords WHERE keywords_id = :keywordId", nativeQuery = true)
    void deleteKeywordRelationships(@Param("keywordId") Long keywordId);

    Optional<Keyword> findTopByOrderByFrequencyDesc();

    Optional<Keyword> findTopByOrderByFrequency();

    List<Keyword> findTop5ByOrderByFrequencyDesc();
}