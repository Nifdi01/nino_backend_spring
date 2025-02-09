package ai.tifosi.nino_backend_spring.repository.user;

import ai.tifosi.nino_backend_spring.model.auth.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, String> {
}