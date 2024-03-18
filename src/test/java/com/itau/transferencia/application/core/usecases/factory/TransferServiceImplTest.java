package com.itau.transferencia.application.core.usecases.factory;

import com.itau.transferencia.adapters.outbound.gateway.impl.BacenGatewayImpl;
import com.itau.transferencia.adapters.outbound.repository.impl.AccountRepository;
import com.itau.transferencia.application.core.domain.dto.AccountDTO;
import com.itau.transferencia.application.core.usecases.rules.AbstractRules.BucketAbstractRule;
import com.itau.transferencia.application.core.usecases.rules.AbstractRules.CacheAbstractRule;
import com.itau.transferencia.application.core.usecases.rules.AbstractRules.TransferAbstractRule;
import com.itau.transferencia.config.cache.RedisClientBuilder;
import io.lettuce.core.api.sync.RedisCommands;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TransferServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private BacenGatewayImpl bacenGateway;

    @Mock
    private RedisCommands<String, String> redisCommands;

    @Mock
    private CacheAbstractRule cacheAbstractRule;

    @Mock
    private BucketAbstractRule bucketAbstractRule;

    @Mock
    private RedisClientBuilder redisClientBuilder;

    @InjectMocks
    private TransferServiceImpl transferService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        BucketAbstractRule bucketRule = mock(BucketAbstractRule.class);
        TransferAbstractRule transferAbstractRule = mock(TransferAbstractRule.class);
        when(bucketRule.tryConsume()).thenReturn(true);
        List<TransferAbstractRule> transferRules = Collections.singletonList(transferAbstractRule);

        when(accountRepository.save(any())).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));
        when(cacheAbstractRule.generateUniqueKey()).thenReturn(UUID.randomUUID().toString());
        when(redisCommands.set("teste", "teste")).thenReturn(null);

        transferService = new TransferServiceImpl(accountRepository, transferRules, bacenGateway, redisClientBuilder, cacheAbstractRule, bucketRule);
    }

    @Test
    public void testTransfer_Successful() {
        AccountDTO fromAccount = new AccountDTO();
        fromAccount.setCurrency(1000.0);
        AccountDTO toAccount = new AccountDTO();
        toAccount.setCurrency(500.0);

        Mono<AccountDTO> resultMono = transferService.transfer(Mono.just(fromAccount), Mono.just(toAccount), 200.0);

        AccountDTO result = resultMono.block();
        assertNotNull(result);
        assertEquals(800.0, result.getCurrency());
    }

    @Test
    public void testTransfer_InsufficientBalance() {
        AccountDTO fromAccount = new AccountDTO();
        fromAccount.setCurrency(100.0);
        AccountDTO toAccount = new AccountDTO();

        assertThrows(ResponseStatusException.class, () -> {
            transferService.transfer(Mono.just(fromAccount), Mono.just(toAccount), 200.0).block();
        });
    }
}
