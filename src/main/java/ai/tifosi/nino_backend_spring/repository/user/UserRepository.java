package ai.tifosi.nino_backend_spring.repository.user;

import ai.tifosi.nino_backend_spring.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}