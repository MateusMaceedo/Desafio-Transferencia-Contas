package com.itau.transferencia.application.core.usecases.rules;

import io.github.bucket4j.Bucket;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BucketLimitRuleTest {
    @Test
    public void testTryConsume() {
        Bucket bucket = mock(Bucket.class);
        when(bucket.tryConsume(1)).thenReturn(true);
        BucketLimitRule bucketLimitRule = new BucketLimitRule(bucket);
        assertTrue(bucketLimitRule.tryConsume());
        verify(bucket, times(1)).tryConsume(1);
    }
}
