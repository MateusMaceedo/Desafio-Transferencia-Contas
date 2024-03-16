package com.itau.transferencia.application.core.usecases.factory;

import com.itau.transferencia.adapters.outbound.repository.impl.AccountRepository;
import com.itau.transferencia.application.core.domain.dto.AccountDTO;
import com.itau.transferencia.application.core.usecases.rules.AbstractRules.TransferAbstractRule;
import com.itau.transferencia.application.core.usecases.rules.AbstractRules.TransferService;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import java.util.List;

public class TransferServiceImpl implements TransferService {
    private final AccountRepository accountRepository;
    private final List<TransferAbstractRule> transferRules;

    public TransferServiceImpl(AccountRepository accountRepository, List<TransferAbstractRule> transferRules) {
        this.accountRepository = accountRepository;
        this.transferRules = transferRules;
    }

    @Override
    @Transactional
    public Mono<AccountDTO> transfer(Mono<AccountDTO> fromAccount, Mono<AccountDTO> toAccount, double amount) {
        return fromAccount.flatMap(account -> {
            for (TransferAbstractRule rule : transferRules) {
                rule.check(account, amount);
            }
            return subtract(fromAccount, amount).then(add(toAccount, amount));
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
}
