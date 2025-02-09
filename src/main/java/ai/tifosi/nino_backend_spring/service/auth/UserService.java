package ai.tifosi.nino_backend_spring.service.auth;

import ai.tifosi.nino_backend_spring.dto.auth.RegisterRequest;
import ai.tifosi.nino_backend_spring.model.auth.Company;
import ai.tifosi.nino_backend_spring.model.auth.User;
import ai.tifosi.nino_backend_spring.repository.auth.CompanyRepository;
import ai.tifosi.nino_backend_spring.repository.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, CompanyRepository companyRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registeruser(RegisterRequest request) {
        Company company = companyRepository.findByNameOrNull(request.getCompanyName());
        User user = new User();

        user.setUsername(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDateJoined(OffsetDateTime.now());

        if (company != null) {
            user.setCompany(company);
            user.setRole("user");
        } else {
            user.setRole("admin");
        }
        return userRepository.save(user);
    }
}
