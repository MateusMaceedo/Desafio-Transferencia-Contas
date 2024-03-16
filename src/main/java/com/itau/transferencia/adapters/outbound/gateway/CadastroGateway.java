package com.itau.transferencia.adapters.outbound.gateway;

import com.itau.transferencia.application.core.domain.dto.AccountDTO;
import reactor.core.publisher.Mono;

public interface CadastroGateway {
    Mono<String> consultarNomeCliente(AccountDTO account);
}
