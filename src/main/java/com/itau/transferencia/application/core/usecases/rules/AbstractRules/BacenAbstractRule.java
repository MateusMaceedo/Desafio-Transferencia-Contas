package com.itau.transferencia.application.core.usecases.rules.AbstractRules;

import com.itau.transferencia.application.core.domain.dto.AccountDTO;
import reactor.core.publisher.Mono;

public interface BacenAbstractRule {
    Mono<String> notifyBacen(AccountDTO fromAccount, AccountDTO toAccount, double amount);
}
