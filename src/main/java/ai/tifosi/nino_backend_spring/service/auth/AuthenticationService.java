package ai.tifosi.nino_backend_spring.service.auth;

import ai.tifosi.nino_backend_spring.Enums.Role;
import ai.tifosi.nino_backend_spring.dto.auth.AuthenticationRequest;
import ai.tifosi.nino_backend_spring.dto.auth.AuthenticationResponse;
import ai.tifosi.nino_backend_spring.dto.auth.CompanyDto;
import ai.tifosi.nino_backend_spring.dto.auth.RegisterRequest;
import ai.tifosi.nino_backend_spring.model.auth.Company;
import ai.tifosi.nino_backend_spring.model.auth.User;
import ai.tifosi.nino_backend_spring.repository.auth.CompanyRepository;
import ai.tifosi.nino_backend_spring.repository.auth.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {
        System.out.println(request);
        User user = new User();
        user.setFirstname(request.getFirstName());
        user.setLastname(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        Company company;
        Optional<Company> existingCompany = companyRepository.findByName(request.getCompanyName());
        if (existingCompany.isPresent()) {
            company = existingCompany.get();
        } else {
            company = new Company(request.getCompanyName());
            companyRepository.save(company);
        }
        user.setCompany(company);
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(jwtToken);

        return response;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmailWithCompany(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(jwtToken);
        response.setFirstName(user.getFirstname());
        response.setLastName(user.getLastname());
        response.setRole(user.getRole());
        response.setCompany(new CompanyDto(user.getCompany()));
        response.setEmail(user.getEmail());

        return response;
    }
}