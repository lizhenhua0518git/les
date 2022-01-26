package com.zkzn.leseureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class LesEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LesEurekaApplication.class, args);
	}

}
