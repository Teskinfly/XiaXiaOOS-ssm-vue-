package com.teskinfly.config;

import com.teskinfly.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Autowired
    RedisConnectionFactory redisConnectionFactory;
    @Bean("categoryRedisTemplate")
    public RedisTemplate categoryRedisTemplate() {
        RedisTemplate categoryTemplate = new RedisTemplate();
        categoryTemplate.setConnectionFactory(redisConnectionFactory);
        categoryTemplate.setKeySerializer(new StringRedisSerializer());
        categoryTemplate.setHashKeySerializer(new StringRedisSerializer());
        categoryTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Category.class));
        return categoryTemplate;
    }
}
