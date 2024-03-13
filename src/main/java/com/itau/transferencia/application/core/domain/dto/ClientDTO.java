package com.itau.transferencia.application.core.domain.dto;

import lombok.*;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class ClientDTO implements Serializable {
    private String cpf;
    private String nome;
    private String email;
    private String telefone;
    private String numeroContaCorrente;
    private int statusConta;
}
