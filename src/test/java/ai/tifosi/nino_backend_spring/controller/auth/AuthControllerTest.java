package ai.tifosi.nino_backend_spring.controller.auth;

import ai.tifosi.nino_backend_spring.dto.auth.LoginRequest;
import ai.tifosi.nino_backend_spring.dto.auth.RegisterRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testRegisterEndpoint() {
        // Create test request body
        RegisterRequest request = new RegisterRequest();
        request.setEmail("test@example.com");
        request.setPassword("SecurePass123");
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setCompanyName("TestCorp");

        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create HTTP entity with body and headers
        HttpEntity<RegisterRequest> entity = new HttpEntity<>(request, headers);

        // Send POST request
        ResponseEntity<Map> response = restTemplate.exchange(
                "/api/auth/register",
                HttpMethod.POST,
                entity,
                Map.class // Expecting a JSON object
        );

        // Log response in case of failure
        if (response.getStatusCode() != HttpStatus.CREATED) {
            System.out.println("Test Failed! Response: " + response.getBody());
        }

        // Verify response status
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // Verify response body
        Map<String, Object> responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals("User created successfully", responseBody.get("message"));
        assertNotNull(responseBody.get("used_id")); // Ensure user_id is returned
    }

    @Test
    public void testLoginEndpoint() {
        // Create test request body
        LoginRequest request = new LoginRequest();
        request.setUsername("test@example.com");
        request.setPassword("SecurePass123");
        request.setCompanyName("TestCorp");

        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create HTTP entity with body and headers
        HttpEntity<LoginRequest> entity = new HttpEntity<>(request, headers);

        // Send POST request
        ResponseEntity<Map> response = restTemplate.exchange(
                "/api/auth/login",
                HttpMethod.POST,
                entity,
                Map.class // Expecting a JSON object
        );

        // Log response in case of failure
        if (response.getStatusCode() != HttpStatus.OK) {
            System.out.println("Test Failed! Response: " + response.getBody());
        }

        // Verify response status
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verify response body
        Map<String, Object> responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals("User Logged in successfully", responseBody.get("message"));
        assertNotNull(responseBody.get("full_name")); // Ensure user_id is returned
        System.out.println(response.getBody());
    }
}