package com.itau.transferencia.application.core.usecases;
;
import com.itau.transferencia.adapters.outbound.gateway.impl.BacenGatewayImpl;
import com.itau.transferencia.adapters.outbound.gateway.impl.CadastroGatewayImpl;
import com.itau.transferencia.adapters.outbound.repository.impl.AccountRepository;
import com.itau.transferencia.application.core.domain.dto.AccountDTO;
import com.itau.transferencia.application.core.domain.dto.TransferAccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;


@Service
public class AccountUseCaseImpl {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CadastroGatewayImpl cadastroGateway;

    @Autowired
    private BacenGatewayImpl bacenGateway;

    @Autowired
    private final KafkaTemplate<String, String> kafkaTemplate;

    public AccountUseCaseImpl(@Qualifier("kafkaTemplate") KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private Mono<AccountDTO> subtract(final Mono<AccountDTO> accountModel, final double value) {
        return accountModel.flatMap(account -> {
            if (account.getCurrency() - value < 0) {
                return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Saldo insuficiente"));
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

    @Transactional
    public Mono<AccountDTO> transfer(final Mono<AccountDTO> fromAccount, final Mono<AccountDTO> toAccount, final double amount) {
        return subtract(fromAccount, amount).then(add(toAccount, amount));
    }

    public Mono<String> transferBetweenAccounts(TransferAccountDTO account) {
        Mono<AccountDTO> fromUser = accountRepository.findById(account.getId()).map(accountModel -> {
                cadastroGateway.getUserName(Mono.just(accountModel));
                    return accountModel;
                }
        );
        Mono<AccountDTO> toAccount = accountRepository.findById(account.getDestination()).map(accountModel -> {
                cadastroGateway.getUserName(Mono.just(accountModel));
                    return accountModel;
                }
        );

        return transfer(fromUser, toAccount, account.getToBeTranfered())
                .then(bacenGateway.notify(fromUser, toAccount, account.getToBeTranfered()))
                .switchIfEmpty(Mono.just(""/*enviar msg para fila de contingencia*/));
    }
}