package com.itau.transferencia.config.services;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.itau.transferencia.adapters.outbound.repository.impl.AccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public AccountRepository accountRepository(AmazonDynamoDB amazonDynamoDB) {
        return new AccountRepository(amazonDynamoDB);
    }
}
