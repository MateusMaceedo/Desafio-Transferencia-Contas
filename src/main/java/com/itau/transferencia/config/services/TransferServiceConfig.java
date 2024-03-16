package com.itau.transferencia.config.services;

import com.itau.transferencia.adapters.outbound.gateway.impl.BacenGatewayImpl;
import com.itau.transferencia.adapters.outbound.repository.impl.AccountRepository;
import com.itau.transferencia.application.core.usecases.factory.TransferServiceImpl;
import com.itau.transferencia.application.core.usecases.rules.AbstractRules.TransferAbstractRule;
import com.itau.transferencia.application.core.usecases.rules.AbstractRules.TransferService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TransferServiceConfig {

    @Bean
    public TransferService transferService(AccountRepository accountRepository, List<TransferAbstractRule> transferRules, BacenGatewayImpl bacenGateway) {
        return new TransferServiceImpl(accountRepository, transferRules, bacenGateway);
    }
}
