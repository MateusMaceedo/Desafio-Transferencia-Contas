package com.itau.transferencia.application.core.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("account")
public class AccountModel {
    @Id
    @Getter
    @Column("id")
    private Integer id;

    @Column("currency")
    private double currency;
}
