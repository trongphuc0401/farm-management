package vn.edu.likelion.farm_management.configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import vn.edu.likelion.farm_management.common.constants.ApiPath;

@Configuration

public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/v1/**")
                        .allowedOrigins( "http://localhost:8080",ApiPath.DOMAIN_LOCAL_FE)
                        .allowedMethods("*")
                        .allowedHeaders("*") //.allowedHeaders("Authorization", "Content-Type", "Accept")
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }
}