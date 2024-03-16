package com.itau.transferencia.application.core.usecases.consulta;

import com.itau.transferencia.application.core.domain.dto.AccountDTO;
import reactor.core.publisher.Mono;

public interface RealizarConsultaDeSaldoUseCase {
    Mono<String> consultarNomeCliente(AccountDTO account);

}
