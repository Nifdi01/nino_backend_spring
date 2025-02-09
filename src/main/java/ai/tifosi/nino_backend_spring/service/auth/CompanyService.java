package ai.tifosi.nino_backend_spring.service.auth;

import ai.tifosi.nino_backend_spring.model.auth.Company;
import ai.tifosi.nino_backend_spring.repository.auth.CompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Transactional
    public Company findByNameOrCreate(String name) {
        return companyRepository.findByName(name)
                .orElseGet(() -> companyRepository.save(new Company(name)));
    }
}
