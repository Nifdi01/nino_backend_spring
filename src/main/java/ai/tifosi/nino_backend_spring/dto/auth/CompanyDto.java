package ai.tifosi.nino_backend_spring.dto.auth;

import ai.tifosi.nino_backend_spring.model.auth.Company;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDto {
    private Long id;
    private String name;

    public CompanyDto(Company company) {
        this.id = company.getId();
        this.name = company.getName();
    }
}
