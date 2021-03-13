package com.code.data.redis.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.Serializable;

/**
 * Redis 配置
 *
 * @author 愆凡
 * @date 2021/3/13 15:30
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
@EnableCaching
public class RedisConfig {

	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.port}")
	private Integer port;

	@Value("${spring.redis.password}")
	private String password;

	@Value("${spring.redis.database}")
	private Integer database;

	@Value("${spring.redis.timeout}")
	private Integer timeout;

	@Value("${spring.redis.jedis.pool.max-active}")
	private Integer maxActive;

	@Value("${spring.redis.jedis.pool.max-wait}")
	private Integer maxWait;

	@Value("${spring.redis.jedis.pool.max-idle}")
	private Integer maxIdle;

	@Value("${spring.redis.jedis.pool.min-idle}")
	private Integer minIdle;

	/* RedisTemplate 配置开始 */

	/**
	 * 默认情况下的模板只能支持 RedisTemplate<String, String>，也就是只能存入字符串，因此支持序列化
	 */
	@Bean
	public RedisTemplate<String, Serializable> redisCacheTemplate(LettuceConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Serializable> template = new RedisTemplate<>();
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}

	/**
	 * 配置使用注解的时候缓存配置，默认是序列化反序列化的形式，加上此配置则为 json 形式
	 */
	@Bean
	public CacheManager cacheManager(RedisConnectionFactory factory) {
		// 配置序列化
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
		RedisCacheConfiguration redisCacheConfiguration = config
				.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

		return RedisCacheManager.builder(factory).cacheDefaults(redisCacheConfiguration).build();
	}

	/* Jedis 配置开始 */

	@Bean
	public JedisPool jedisPool() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(maxActive);
		poolConfig.setMaxWaitMillis(maxWait);
		poolConfig.setMaxIdle(maxIdle);
		poolConfig.setMinIdle(minIdle);

		return new JedisPool(poolConfig, host, port, timeout, password, database);
	}
}
