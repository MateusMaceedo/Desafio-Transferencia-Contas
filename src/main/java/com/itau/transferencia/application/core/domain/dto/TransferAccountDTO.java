package com.itau.transferencia.application.core.domain.dto;

import lombok.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
public class TransferAccountDTO implements Serializable {
    private String id;
    private String cpfCliente;
    private String valorTransferencia;
    private Date dataTransferencia;
    private String identificadorBacenTransferencia;
    private String numeroContaDestino;
    private String sucesso;
    private Long dataHoraExpiracaoDado;
    private double toBeTranfered;
    private String destination;
}
