package com.itau.transferencia.application.core.usecases.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itau.transferencia.adapters.outbound.gateway.impl.BacenGatewayImpl;
import com.itau.transferencia.adapters.outbound.repository.impl.AccountRepository;
import com.itau.transferencia.application.core.domain.dto.AccountDTO;
import com.itau.transferencia.application.core.usecases.rules.AbstractRules.BucketAbstractRule;
import com.itau.transferencia.application.core.usecases.rules.AbstractRules.CacheAbstractRule;
import com.itau.transferencia.application.core.usecases.rules.AbstractRules.TransferAbstractRule;
import com.itau.transferencia.application.core.usecases.rules.AbstractRules.TransferService;
import com.itau.transferencia.application.core.usecases.rules.BacenNotification;
import com.itau.transferencia.config.cache.RedisClientBuilder;
import io.lettuce.core.api.sync.RedisCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import java.util.List;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import java.time.Duration;

@Service
public class TransferServiceImpl implements TransferService {

    private final AccountRepository accountRepository;
    private final List<TransferAbstractRule> transferRules;
    private final BacenGatewayImpl bacenGateway;
    private final Bucket bucket;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RedisCommands<String, String> redisCommands;
    private static final String KEY_NOTIFICATION = "BacenNotification:";
    private final CacheAbstractRule cacheAbstractRule;
    private final BucketAbstractRule bucketAbstractRule;

    @Autowired
    public TransferServiceImpl(AccountRepository accountRepository, List<TransferAbstractRule> transferRules, BacenGatewayImpl bacenGateway, RedisClientBuilder redisClientBuilder, CacheAbstractRule cacheAbstractRule, BucketAbstractRule bucketAbstractRule) {
        this.accountRepository = accountRepository;
        this.transferRules = transferRules;
        this.bacenGateway = bacenGateway;
        this.cacheAbstractRule = cacheAbstractRule;
        this.bucketAbstractRule = bucketAbstractRule;
        this.bucket = createBucket();
        this.redisCommands = redisClientBuilder.redisClient.connect().sync();
    }

    private Bucket createBucket() {
        Bandwidth limit = Bandwidth.classic(10, Refill.intervally(10, Duration.ofMinutes(1)));
        return Bucket.builder().addLimit(limit).build();
    }

    @Transactional
    public Mono<AccountDTO> transfer(Mono<AccountDTO> fromAccount, Mono<AccountDTO> toAccount, double amount) {
        return fromAccount.flatMap(account -> {
            for (TransferAbstractRule rule : transferRules) {
                rule.check(account, amount);
            }
            return subtract(fromAccount, amount)
                    .then(add(toAccount, amount))
                    .flatMap(result -> {
                        // Notificar o BACEN após a transferência
                        return notifyBacenSync(fromAccount.block(), toAccount.block(), amount)
                                .thenReturn(result);
                    });
        });
    }

    private Mono<AccountDTO> subtract(final Mono<AccountDTO> accountModel, final double value) {
        return accountModel.flatMap(account -> {
            if (account.getCurrency() - value < 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Saldo insuficiente");
            }
            account.setCurrency(account.getCurrency() - value);
            return accountRepository.save(account);
        });
    }

    private Mono<AccountDTO> add(final Mono<AccountDTO> accountModel, final double value) {
        return accountModel.map(account -> {
            account.setCurrency(account.getCurrency() + value);
            return account;
        }).flatMap(accountRepository::save);
    }

    private Mono<String> notifyBacenSync(AccountDTO fromAccount, AccountDTO toAccount, double amount) {
        if (!bucketAbstractRule.tryConsume()) {
            throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS, "Limite de requisições excedido");
        }

        try {
            BacenNotification notification = new BacenNotification(fromAccount, toAccount, amount);
            String serializedNotification = objectMapper.writeValueAsString(notification);
            String key = KEY_NOTIFICATION + cacheAbstractRule.generateUniqueKey();
            redisCommands.set(key, serializedNotification);
            return bacenGateway.notify(Mono.just(fromAccount), Mono.just(toAccount), amount);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao serializar e armazenar notificação no Redis", e);
        }
    }
}
