package com.itau.transferencia.application.core.domain.dto;

import lombok.Data;

@Data
public class TransferAccountDTO {
    private int id;
    private double toBeTranfered;
    private int destination;
}
