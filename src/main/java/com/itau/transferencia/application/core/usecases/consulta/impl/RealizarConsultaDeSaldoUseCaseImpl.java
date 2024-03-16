package com.itau.transferencia.application.core.usecases.consulta.impl;

import com.itau.transferencia.adapters.outbound.gateway.CadastroGateway;
import com.itau.transferencia.application.core.domain.dto.AccountDTO;
import com.itau.transferencia.application.core.usecases.consulta.RealizarConsultaDeSaldoUseCase;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class RealizarConsultaDeSaldoUseCaseImpl implements RealizarConsultaDeSaldoUseCase {

    private final CadastroGateway cadastroGateway;

    public RealizarConsultaDeSaldoUseCaseImpl(CadastroGateway cadastroGateway) {
        this.cadastroGateway = cadastroGateway;
    }

    @Override
    public Mono<String> consultarNomeCliente(AccountDTO account) {

        cadastroGateway.consultarNomeCliente(account);

        if (!account.isAtiva()) {
            return Mono.error(new RuntimeException("Conta corrente não está ativa"));
        }

        if (account.getLimiteDisponivel() <= 0.0) {
            return Mono.error(new RuntimeException("Cliente não possui limite disponível para realizar a transferência"));
        }

        return cadastroGateway.consultarNomeCliente(account);
    }
}
