package com.zkzn.les.tactics.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer{
	static final String ORIGINS[] = new String[] { "GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS" };
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowCredentials(true)	
        		.allowedMethods(ORIGINS)
        		.allowedHeaders("*")
        		.exposedHeaders("Authorization", "access-control-request-headers")
                .maxAge(3600);
        
    }
}
