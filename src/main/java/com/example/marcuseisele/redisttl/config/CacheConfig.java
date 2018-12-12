package com.example.marcuseisele.redisttl.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
@Slf4j
public class CacheConfig {

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {

        log.info("Redis (/Jedis) local configuration enabled.");
        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();

        // Defaults
        redisConnectionFactory.setHostName("localhost");
        redisConnectionFactory.setPort(6379);
        return redisConnectionFactory;
    }


    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        return template;
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setDefaultExpiration(2);
        cacheManager.setCacheNames(Arrays.asList("Cache1","Cache2"));
        Map<String, Long> expirationTimeInSeconds = new HashMap<>();
        //Cache 1 has 5s TTL, Cache2 has 10s TTL
        expirationTimeInSeconds.put("Cache1", 5L);
        expirationTimeInSeconds.put("Cache2", 10L);
        cacheManager.setExpires(expirationTimeInSeconds);

        //intentionally to again allow dynamic cache creation with default cacheTime
        cacheManager.setCacheNames(null);
        return cacheManager;
    }
}
