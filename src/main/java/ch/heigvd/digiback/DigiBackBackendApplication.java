package ch.heigvd.digiback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DigiBackBackendApplication {

    /**
     * The main entry point of the application.
     * @param args Command-line arguments, passed when the app is run.
     */
    public static void main(String[] args) {
        SpringApplication.run(DigiBackBackendApplication.class, args);
    }
}
