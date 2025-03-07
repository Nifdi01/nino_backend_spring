package ai.tifosi.nino_backend_spring.repository.news;

import ai.tifosi.nino_backend_spring.model.news.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    @EntityGraph(attributePaths = {"source", "source.platform", "keywords"})
    @Query("SELECT n FROM News n " +
            "WHERE (:search IS NULL OR LOWER(n.title) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(n.content) LIKE LOWER(CONCAT('%', :search, '%'))) " +
            "AND (:sourceNames IS NULL OR n.source.name IN :sourceNames) " +
            "AND (:platformNames IS NULL OR n.source.platform.name IN :platformNames) " +
            "AND (:keywordNames IS NULL OR EXISTS (SELECT 1 FROM n.keywords k WHERE LOWER(k.name) IN :keywordNames)) " +
            "AND (n.publishedAt BETWEEN :startDate AND :endDate)")
    Page<News> findByFilters(
            @Param("search") String search,
            @Param("sourceNames") List<String> sourceNames,
            @Param("platformNames") List<String> platformNames,
            @Param("keywordNames") List<String> keywordNames,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            Pageable pageable
    );
}