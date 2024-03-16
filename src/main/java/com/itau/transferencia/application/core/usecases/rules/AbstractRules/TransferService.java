package com.itau.transferencia.application.core.usecases.rules.AbstractRules;

import com.itau.transferencia.application.core.domain.dto.AccountDTO;
import reactor.core.publisher.Mono;

public interface TransferService {
    Mono<AccountDTO> transfer(Mono<AccountDTO> fromAccount, Mono<AccountDTO> toAccount, double amount);
}
