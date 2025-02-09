package ai.tifosi.nino_backend_spring.repository.auth;

import ai.tifosi.nino_backend_spring.model.auth.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {
    Optional<Company> findByName(String name);

    default Company findByNameOrNull(String name) {
        return findByName(name).orElse(null);
    }
}