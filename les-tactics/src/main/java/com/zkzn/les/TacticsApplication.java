package com.zkzn.les;

import com.zkzn.les.common.util.lang.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/***
 * @Discription: 策略服务启动类
 */
@EnableFeignClients
@ServletComponentScan
@EnableEurekaClient
@SpringBootApplication
public class TacticsApplication
{
    public static void main(String[] args) {
        SpringApplication.run(TacticsApplication.class, args);
    }

    @Bean
    public SpringUtil getSpringUtil2() {
        return new SpringUtil();
    }
}
