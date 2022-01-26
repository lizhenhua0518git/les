package com.zkzn.les.stock.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DateSourceConfig {

	
	@ConfigurationProperties(prefix = "spring.datasource")
	@Bean(name="druidDataSource",initMethod="init")
	public DruidDataSource druidDataSource(){
	      return DruidDataSourceBuilder.create().build();
	}
	
}
