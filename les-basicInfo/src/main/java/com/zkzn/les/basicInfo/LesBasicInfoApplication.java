package com.zkzn.les.basicInfo;

import com.zkzn.les.basicInfo.config.ImgToBase64Servlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableEurekaClient
@EnableCaching
@ServletComponentScan
public class LesBasicInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LesBasicInfoApplication.class, args);
	}

}
