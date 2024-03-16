package com.itau.transferencia.application.core.domain.dto;

import lombok.*;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class AccountDTO implements Serializable {
    private String cpfCliente;
    private String numeroContaCorrente;
    private String saldoAtual;
    private String sucesso;
}
