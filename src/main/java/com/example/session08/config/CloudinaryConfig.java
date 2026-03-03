package com.example.session08.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dq6f7y2tu");
        config.put("api_key", "246414255899469");
        config.put("api_secret", "GIO4Mej4crWrNZUNAd_erxt-v2g");
        return new Cloudinary(config);
    }
}
