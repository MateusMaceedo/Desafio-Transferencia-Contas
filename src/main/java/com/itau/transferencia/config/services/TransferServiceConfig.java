package com.itau.transferencia.config.services;

import com.itau.transferencia.adapters.outbound.gateway.impl.BacenGatewayImpl;
import com.itau.transferencia.adapters.outbound.repository.impl.AccountRepository;
import com.itau.transferencia.application.core.usecases.factory.TransferServiceImpl;
import com.itau.transferencia.application.core.usecases.rules.AbstractRules.BucketAbstractRule;
import com.itau.transferencia.application.core.usecases.rules.AbstractRules.CacheAbstractRule;
import com.itau.transferencia.application.core.usecases.rules.AbstractRules.TransferAbstractRule;
import com.itau.transferencia.application.core.usecases.rules.AbstractRules.TransferService;
import com.itau.transferencia.config.cache.RedisClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TransferServiceConfig {

    @Bean
    public TransferService transferService(AccountRepository accountRepository, List<TransferAbstractRule> transferRules, BacenGatewayImpl bacenGateway, RedisClientBuilder redisClientBuilder, CacheAbstractRule cacheAbstractRule
    ,BucketAbstractRule bucketAbstractRule) {
        return new TransferServiceImpl(accountRepository, transferRules, bacenGateway, redisClientBuilder, cacheAbstractRule, bucketAbstractRule);
    }
}
