package com.itau.transferencia.config.services;

import com.itau.transferencia.adapters.outbound.gateway.impl.BacenGatewayImpl;
import com.itau.transferencia.adapters.outbound.repository.impl.AccountRepository;
import com.itau.transferencia.application.core.usecases.rules.AbstractRules.BucketAbstractRule;
import com.itau.transferencia.application.core.usecases.rules.AbstractRules.CacheAbstractRule;
import com.itau.transferencia.application.core.usecases.rules.AbstractRules.TransferAbstractRule;
import com.itau.transferencia.application.core.usecases.rules.AbstractRules.TransferService;
import com.itau.transferencia.config.cache.RedisClientBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TransferServiceConfigTest {
    @Test
    void testTransferServiceBeanCreation() {
        AccountRepository accountRepositoryMock = Mockito.mock(AccountRepository.class);
        BacenGatewayImpl bacenGatewayMock = Mockito.mock(BacenGatewayImpl.class);
        RedisClientBuilder redisClientBuilderMock = Mockito.mock(RedisClientBuilder.class);
        CacheAbstractRule cacheAbstractRuleMock = Mockito.mock(CacheAbstractRule.class);
        BucketAbstractRule bucketAbstractRuleMock = Mockito.mock(BucketAbstractRule.class);
        List<TransferAbstractRule> transferRulesMock = Collections.singletonList(Mockito.mock(TransferAbstractRule.class));

        TransferServiceConfig transferServiceConfig = new TransferServiceConfig();

        TransferService transferService = transferServiceConfig.transferService(
                accountRepositoryMock, transferRulesMock, bacenGatewayMock, redisClientBuilderMock, cacheAbstractRuleMock, bucketAbstractRuleMock
        );

        assertNotNull(transferService);
    }
}
