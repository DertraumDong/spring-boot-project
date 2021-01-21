package com.dtr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @description:
 * @author:DerTraum
 * @date:2021/1/19
 * @since
 */
@Configuration
public class RedisConfig {

    @Bean(name = "redisTemplate")
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }
}
