package com.itau.transferencia.application.core.usecases.rules;

import com.itau.transferencia.application.core.domain.dto.AccountDTO;

public class BacenNotification {

    private final AccountDTO fromAccount;
    private final AccountDTO toAccount;
    private final double amount;

    public BacenNotification(AccountDTO fromAccount, AccountDTO toAccount, double amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    public AccountDTO getFromAccount() {
        return fromAccount;
    }

    public AccountDTO getToAccount() {
        return toAccount;
    }

    public double getAmount() {
        return amount;
    }
}
