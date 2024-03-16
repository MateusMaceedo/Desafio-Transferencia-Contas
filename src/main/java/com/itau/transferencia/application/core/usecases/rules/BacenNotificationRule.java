package com.itau.transferencia.application.core.usecases.rules;

import com.itau.transferencia.adapters.outbound.gateway.impl.BacenGatewayImpl;
import com.itau.transferencia.application.core.domain.dto.AccountDTO;
import com.itau.transferencia.application.core.usecases.rules.AbstractRules.BacenAbstractRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

public class BacenNotificationRule implements BacenAbstractRule {

    @Autowired
    private BacenGatewayImpl bacenGateway;

    public Mono<String> notifyBacen(AccountDTO fromAccount, AccountDTO toAccount, double amount) {
        return bacenGateway.notify(Mono.just(fromAccount), Mono.just(toAccount), amount)
                .flatMap(response -> {
                    if (response.isEmpty()) {
                        return Mono.error(new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS, "Rate limit excedido ao notificar o BACEN"));
                    } else {
                        return Mono.just(response);
                    }
                })
                .onErrorResume(error -> {
                    return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao notificar o BACEN: " + error.getMessage()));
                });
    }
}
