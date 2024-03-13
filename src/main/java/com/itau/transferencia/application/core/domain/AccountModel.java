package com.itau.transferencia.application.core.domain;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
public class AccountModel {
    private String id;
    private double currency;
}
