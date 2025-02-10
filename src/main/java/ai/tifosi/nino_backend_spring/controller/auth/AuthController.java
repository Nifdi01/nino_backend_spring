package ai.tifosi.nino_backend_spring.controller.auth;

import ai.tifosi.nino_backend_spring.dto.auth.LoginRequest;
import ai.tifosi.nino_backend_spring.dto.auth.RegisterRequest;
import ai.tifosi.nino_backend_spring.model.auth.User;
import ai.tifosi.nino_backend_spring.service.auth.UserService;
import ai.tifosi.nino_backend_spring.util.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final JwtUtils jwtUtils;

    @Autowired
    public AuthController(UserService userService, JwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        System.out.println("REQUEST BODY: " + " " + registerRequest);
        try {
            User newUser = userService.registerUser(registerRequest);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of(
                            "message", "User created successfully",
                            "used_id", newUser.getId()
                    ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of(
                            "error", e.getMessage()
                    ));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println("REQUEST BODY: " + " " + loginRequest);
        try {
            User user = userService.loginUser(loginRequest);

            String accessToken = jwtUtils.generateAccessToken(user.getUsername(), user.getRole());
            String refreshToken = jwtUtils.generateRefreshToken(user.getUsername());

            String fullName = user.getFirstName() + " " + user.getLastName();

            System.out.println("ACCESS: " + accessToken);
            System.out.println("REFRESH: " + refreshToken);

            return ResponseEntity.ok(Map.of(
                    "message", "User Logged in successfully",
                    "first_name", user.getFirstName(),
                    "last_name", user.getLastName(),
                    "full_name", fullName,
                    "email", user.getEmail(),
                    "company", user.getCompany().getName(),
                    "role", user.getRole(),
                    "refresh", refreshToken,
                    "access", accessToken
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
