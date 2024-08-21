package vn.edu.likelion.farm_management.config;


import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    private final String CLOUD_NAME = "dziu6t7jd";
    private final String API_KEY = "291423369574436";
    private final String API_SECRET = "xYH4K8A803dMFvdamtlnGZRc-Z4";
    private final String CLOUDINARY_URL= "cloudinary://291423369574436:xYH4K8A803dMFvdamtlnGZRc-Z4@dziu6t7jd";


    @Bean
    public Cloudinary cloudinary(){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name",CLOUD_NAME);
        config.put("api_key",API_KEY);
        config.put("api_secret",API_SECRET);

        return new Cloudinary(config);
    }
}