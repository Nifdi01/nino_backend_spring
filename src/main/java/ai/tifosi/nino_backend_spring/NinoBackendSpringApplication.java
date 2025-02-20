package ai.tifosi.nino_backend_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "ai.tifosi.nino_backend_spring.model")  // Adjust this to your entity package
@EnableJpaRepositories(basePackages = "ai.tifosi.nino_backend_spring.repository")
// Adjust this to your repository package
public class NinoBackendSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(NinoBackendSpringApplication.class, args);
    }
}

