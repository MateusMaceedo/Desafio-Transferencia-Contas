package com.itau.transferencia.application.core.usecases.factory;

import com.itau.transferencia.adapters.outbound.gateway.impl.BacenGatewayImpl;
import com.itau.transferencia.adapters.outbound.repository.impl.AccountRepository;
import com.itau.transferencia.application.core.domain.dto.AccountDTO;
import com.itau.transferencia.application.core.usecases.rules.AbstractRules.TransferAbstractRule;
import com.itau.transferencia.application.core.usecases.rules.AbstractRules.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import java.util.List;

@Service
public class TransferServiceImpl implements TransferService {

    private final AccountRepository accountRepository;
    private final List<TransferAbstractRule> transferRules;
    private final BacenGatewayImpl bacenGateway;

    @Autowired
    public TransferServiceImpl(AccountRepository accountRepository, List<TransferAbstractRule> transferRules, BacenGatewayImpl bacenGateway) {
        this.accountRepository = accountRepository;
        this.transferRules = transferRules;
        this.bacenGateway = bacenGateway;
    }

    @Override
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
        return bacenGateway.notify(Mono.just(fromAccount), Mono.just(toAccount), amount);
    }
}
