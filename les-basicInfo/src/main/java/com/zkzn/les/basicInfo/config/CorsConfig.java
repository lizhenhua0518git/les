package com.zkzn.les.basicInfo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    static final String ORIGINS[] = new String[] {"GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS"};
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods(ORIGINS)
                .allowedHeaders("*")
                .maxAge(3600);
    }
}
