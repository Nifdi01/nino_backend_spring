package ai.tifosi.nino_backend_spring.dto.auth;

import ai.tifosi.nino_backend_spring.Enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponse {
    private String token;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private String company;


    @Override
    public String toString() {
        return "AuthenticationResponse{" +
                "token='" + token + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", company=" + company +
                '}';
    }
}