package com.zkzn.les.tactics.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @ClassName RedissonConfiguration
 * @Description TODO
 * @Author zhanglei
 * Date 2021/6/29 10:23
 * @Version 1.0
 **/
@Component
public class RedissonConfiguration {
//    @Value("${redisson.address}")
//    private String addressUrl;

//    @Bean
//    public RedissonClient getRedisson() {
//        Config config = new Config();
//        config.useSingleServer().setAddress(addressUrl)
//                .setTimeout(1000)
//                .setRetryAttempts(3)
//                .setRetryInterval(1000)
//                .setPingConnectionInterval(2000)
//                .setConnectionMinimumIdleSize(10)
//                .setDatabase(3);
//        RedissonClient redisson = Redisson.create(config);
//        return redisson;
//    }
}
