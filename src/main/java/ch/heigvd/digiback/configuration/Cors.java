package ch.heigvd.digiback.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Cors implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // We're an open API.
        registry.addMapping("/**").allowedMethods("DELETE", "GET", "POST", "PUT");
    }
}
