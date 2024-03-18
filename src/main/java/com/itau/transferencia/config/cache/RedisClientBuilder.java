package com.itau.transferencia.config.cache;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisClientBuilder {

    @Value("${redis.uri}") String uri;

    @Bean
    public RedisClient redisClient() {
        return RedisClient.create(uri);
    }

    @Bean
    public StatefulRedisConnection<String, String> connection() {
        return redisClient().connect();
    }
}