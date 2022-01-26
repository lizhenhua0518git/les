package com.lesweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableConfigurationProperties
@SpringBootApplication
public class LesWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(LesWebApplication.class, args);
	}

	@Bean
    public InternalResourceViewResolver htmlViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setSuffix(".html");
        viewResolver.setOrder(0);
        viewResolver.setContentType("text/html;charset=UTF-8");
        return viewResolver;
    }

}
