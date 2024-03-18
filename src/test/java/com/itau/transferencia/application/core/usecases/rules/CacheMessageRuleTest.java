package com.itau.transferencia.application.core.usecases.rules;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CacheMessageRuleTest {
    @Test
    public void testGenerateUniqueKey() {
        CacheMessageRule cacheMessageRule = new CacheMessageRule();

        String key1 = cacheMessageRule.generateUniqueKey();
        String key2 = cacheMessageRule.generateUniqueKey();

        assertNotEquals(key1, key2);
    }
}
