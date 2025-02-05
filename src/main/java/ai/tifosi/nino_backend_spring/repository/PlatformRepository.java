package ai.tifosi.nino_backend_spring.repository;

import ai.tifosi.nino_backend_spring.model.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatformRepository extends JpaRepository<Platform, Long> {
}