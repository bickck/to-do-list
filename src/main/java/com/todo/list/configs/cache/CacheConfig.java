package com.todo.list.configs.cache;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.CacheProperties.Redis;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleCacheResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.todo.list.CacheNames;
import com.todo.list.RedisCacheManagerName;

@EnableCaching
@EnableRedisRepositories
@Configuration
public class CacheConfig implements CachingConfigurer, RedisCacheManagerName, CacheNames {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private RedisProperties properties;

	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		return new LettuceConnectionFactory(properties.getHost(), properties.getPort());
	}

	@Bean
	public RedisTemplate<Object, Object> redisTemplate() {
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		redisTemplate.setKeySerializer(new GenericJackson2JsonRedisSerializer());
		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

		return redisTemplate;
	}

	@Bean(name = TodoCacheManagerName)
	public CacheManager todoCacheManager() {
		RedisCacheManager.RedisCacheManagerBuilder cacheManager = RedisCacheManager.RedisCacheManagerBuilder
				.fromConnectionFactory(redisConnectionFactory());

		RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.java()))
				.entryTtl(Duration.ofMinutes(30));

		// new GenericJackson2JsonRedisSerializer();
		// RedisSerializer.java()
		cacheManager.withCacheConfiguration(TodoCacheName, cacheConfiguration);

		return cacheManager.build();
	}

	@Bean(name = QuoteCacheManagerName)
	public CacheManager quoteCacheManager() {
		RedisCacheManager.RedisCacheManagerBuilder cacheManager = RedisCacheManager.RedisCacheManagerBuilder
				.fromConnectionFactory(redisConnectionFactory());

		RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.java()))
				.entryTtl(Duration.ofMinutes(30));
		cacheManager.withCacheConfiguration(QuoteCacheName, cacheConfiguration);
		return cacheManager.build();
	}

	@Bean(name = TestCacheManagerName)
	public CacheManager testCacheeManager() {
		RedisCacheManager.RedisCacheManagerBuilder cacheManager = RedisCacheManager.RedisCacheManagerBuilder
				.fromConnectionFactory(redisConnectionFactory());

		RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.java()))
				.entryTtl(Duration.ofMinutes(30));
		cacheManager.withCacheConfiguration(testCacheName, cacheConfiguration);
		return cacheManager.build();
	}

}
