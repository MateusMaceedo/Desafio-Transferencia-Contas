package com.itau.transferencia.application.core.domain.dto;

import lombok.*;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class AccountDTO implements Serializable {
    private String cpfCliente;
    private String numeroContaCorrente;
    private String saldoAtual;
    private String sucesso;
    private boolean ativa;
    private double limiteDisponivel;
    private double currency;
    private String id;
    private Mono<Double> mono;

    public double getLimiteDisponivel() {
        return limiteDisponivel;
    }

    public Double getBalance() {
        return mono.block();
    }
}
