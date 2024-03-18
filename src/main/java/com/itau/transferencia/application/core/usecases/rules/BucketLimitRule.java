package com.itau.transferencia.application.core.usecases.rules;

import com.itau.transferencia.application.core.usecases.rules.AbstractRules.BucketAbstractRule;
import io.github.bucket4j.Bucket;

public class BucketLimitRule implements BucketAbstractRule {

    private final Bucket bucket;

    public BucketLimitRule(Bucket bucket) {
        this.bucket = bucket;
    }

    public boolean tryConsume() {
        return bucket.tryConsume(1);
    }
}
