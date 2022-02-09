package com.zkzn.les.oms.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

@Configuration
public class DateSourceConfig {

	
	@ConfigurationProperties(prefix = "spring.datasource")
	@Bean(name="druidDataSource",initMethod="init")
	public DruidDataSource druidDataSource(){
	      return DruidDataSourceBuilder.create().build();
	}
	
}
