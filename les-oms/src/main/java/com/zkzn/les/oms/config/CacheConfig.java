package com.zkzn.les.oms.config;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**.
 * 
 * redis注解缓存类配置
 * @author wangzhou
 *
 */
@Configuration
public class CacheConfig {

	@Bean
	CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
		RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();

		//common信息缓存配置
		RedisCacheConfiguration userCacheConfiguration = defaultCacheConfig
				// 设置 key为string序列化
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
				// 设置value为json序列化
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())).disableCachingNullValues();
		Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
		//entryTtl设置缓存失效时间，单位是秒
		redisCacheConfigurationMap.put("common", userCacheConfiguration.entryTtl(Duration.ofSeconds(30)));



		Set<String> cacheNames = new HashSet<>();
		cacheNames.add("common");
		//初始化RedisCacheManager
		RedisCacheManager cacheManager = RedisCacheManager.builder(connectionFactory).cacheDefaults(defaultCacheConfig).initialCacheNames(cacheNames).withInitialCacheConfigurations(redisCacheConfigurationMap).build();
		return cacheManager;
	}
}
