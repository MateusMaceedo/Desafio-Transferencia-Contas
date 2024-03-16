package com.itau.transferencia.adapters.outbound.gateway.impl;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.itau.transferencia.adapters.outbound.gateway.CadastroGateway;
import com.itau.transferencia.application.core.domain.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CadastroGatewayImpl implements CadastroGateway {

    @Autowired
    private WireMockServer wireMockServer;
    public Flux<String> getUserName(final Mono<AccountDTO> accountId) {
        return WebClient.create(wireMockServer.baseUrl())
                .get()
                .uri("/user")
                .exchangeToFlux(clientResponse -> clientResponse.bodyToFlux(String.class));
    }

    @Override
    public Mono<String> consultarNomeCliente(AccountDTO account) {
        return getUserName(Mono.just(account)).next();
    }
}

