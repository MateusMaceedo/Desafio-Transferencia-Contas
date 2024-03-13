package com.itau.transferencia.application.core.domain.dto;

import lombok.*;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class ReceptionDTO implements Serializable {
    private Integer identificadorRecepcao;
    private String hashTransacao;
    private Integer codigoBancoOrigem;
}
