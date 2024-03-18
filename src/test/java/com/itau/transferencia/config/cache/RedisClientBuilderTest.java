package com.itau.transferencia.config.cache;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class RedisClientBuilderTest {
    @Mock
    private RedisClient redisClient;

    @Mock
    private StatefulRedisConnection<String, String> redisConnection;

    @InjectMocks
    private RedisClientBuilder redisClientBuilder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRedisClientBean() {
        assertNotNull(redisClientBuilder.redisClient());
    }

    @Test
    void testConnectionBean() {
        when(redisClient.connect()).thenReturn(redisConnection);
        assertNotNull(redisClientBuilder.connection());
    }
}
