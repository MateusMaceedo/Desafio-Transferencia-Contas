package com.itau.transferencia.application.core.usecases.rules;

import com.itau.transferencia.application.core.domain.dto.AccountDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BacenNotification {

    private final AccountDTO fromAccount;
    private final AccountDTO toAccount;
    private final double amount;

    public BacenNotification(AccountDTO fromAccount, AccountDTO toAccount, double amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }
}
