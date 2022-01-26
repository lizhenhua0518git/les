package com.zkzn.les.wms.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import com.google.common.collect.Lists;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DateSourceConfig {

	
	@ConfigurationProperties(prefix = "spring.datasource")
	@Bean(name="druidDataSource",initMethod="init")
	public DruidDataSource druidDataSource(){

		DruidDataSource druidDataSource = new DruidDataSource();

		List filterList= Lists.newArrayList();

		filterList.add(wallFilter());

		druidDataSource.setProxyFilters(filterList);

		return druidDataSource;
	}

	@Bean

	public WallFilter wallFilter(){

		WallFilter wallFilter=new WallFilter();

		wallFilter.setConfig(wallConfig());

		return wallFilter;

	}

	@Bean

	public WallConfig wallConfig(){

		WallConfig config =new WallConfig();

		config.setMultiStatementAllow(true);//允许一次执行多条语句

		config.setNoneBaseStatementAllow(true);//允许非基本语句的其他语句

		return config;

	}
	
}
