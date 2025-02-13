package ai.tifosi.nino_backend_spring.service.auth;

import ai.tifosi.nino_backend_spring.model.auth.Company;
import ai.tifosi.nino_backend_spring.repository.auth.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public Company createCompany(String name) {
        Company company = new Company(name);

        return companyRepository.save(company);
    }
}