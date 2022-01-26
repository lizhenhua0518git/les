package com.zkzn.les;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
@SpringBootApplication
@SpringBootConfiguration
@EnableFeignClients
@EnableDiscoveryClient
@EnableEurekaClient
@EnableCaching
@EnableResourceServer
public class StockApplication
{
    public static void main(String[] args) {
        SpringApplication.run(StockApplication.class, args);
    }
}
