//package com.itau.transferencia.config.cache;
//
//import io.lettuce.core.RedisClient;
//import io.lettuce.core.RedisURI;
//import io.lettuce.core.api.StatefulRedisConnection;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import javax.inject.Inject;
//
//@Configuration
//public class RedisClientBuilder {
//
//    @Value("${redis.uri}") String uri;
//
//    @Bean
//    public StatefulRedisConnection<String, String> connection() {
//        RedisClient redisClient = RedisClient.create(uri);
//        return redisClient.connect();
//    }
//
//    @Inject
//    public RedisClient redisClient = RedisClient.
//            create(RedisURI.builder().
//                    withHost("localhost").
//                    withPort(6379).
//                    build());
//}
