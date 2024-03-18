package com.itau.transferencia.application.core.usecases.rules;

import com.itau.transferencia.application.core.usecases.rules.AbstractRules.CacheAbstractRule;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class CacheMessageRule implements CacheAbstractRule {

    public String generateUniqueKey() {
        return UUID.randomUUID().toString();
    }
}
