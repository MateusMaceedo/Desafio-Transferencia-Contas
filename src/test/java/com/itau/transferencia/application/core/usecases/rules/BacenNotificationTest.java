package com.itau.transferencia.application.core.usecases.rules;

import com.itau.transferencia.application.core.domain.dto.AccountDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BacenNotificationTest {
    @Test
    public void testBacenNotificationConstructorAndGetters() {
        AccountDTO fromAccount = new AccountDTO();
        fromAccount.setNumeroContaCorrente("123456");
        fromAccount.setSaldoAtual("1000.00");
        AccountDTO toAccount = new AccountDTO();
        toAccount.setNumeroContaCorrente("654321");
        toAccount.setSaldoAtual("500.00");
        double amount = 200.0;

        BacenNotification bacenNotification = new BacenNotification(fromAccount, toAccount, amount);

        assertEquals(fromAccount, bacenNotification.getFromAccount());
        assertEquals(toAccount, bacenNotification.getToAccount());
        assertEquals(amount, bacenNotification.getAmount());
    }
}
