package com.zkzn.les;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName WmsApplication
 * @Description TODO
 * @Author zhanglei
 * Date 2021/6/10 10:35
 * @Version 1.0
 **/
@EnableFeignClients
@ServletComponentScan
@EnableEurekaClient
@SpringBootApplication
public class WmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(WmsApplication.class, args);
    }
}