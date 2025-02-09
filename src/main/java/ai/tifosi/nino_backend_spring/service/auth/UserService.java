package ai.tifosi.nino_backend_spring.service.auth;

import ai.tifosi.nino_backend_spring.dto.auth.LoginRequest;
import ai.tifosi.nino_backend_spring.dto.auth.RegisterRequest;
import ai.tifosi.nino_backend_spring.model.auth.Company;
import ai.tifosi.nino_backend_spring.model.auth.User;
import ai.tifosi.nino_backend_spring.repository.auth.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final CompanyService companyService;  // Use service instead of repository
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, CompanyService companyService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.companyService = companyService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User registerUser(RegisterRequest request) {
        // Find or create the company
        Company company = companyService.findByNameOrCreate(request.getCompanyName());

        User user = new User();
        user.setUsername(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDateJoined(OffsetDateTime.now());

        // Determine role based on whether the company was newly created
        if (company.getId() == null) {  // Newly created companies should assign the user as "admin"
            user.setRole("admin");
        } else {
            user.setRole("user");
        }

        user.setCompany(company);
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User loginUser(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        if (user.getCompany() == null || !loginRequest.getCompanyName().equalsIgnoreCase(user.getCompany().getName())) {
            throw new RuntimeException("Invalid company name");
        }

        return user;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

