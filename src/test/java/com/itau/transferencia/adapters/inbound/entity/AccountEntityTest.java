package com.itau.transferencia.adapters.inbound.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AccountEntityTest {

    @Test
    public void testAccountEntityInitialization() {
        AccountEntity accountEntity = new AccountEntity();
        assertNull(accountEntity.getCpfCliente());
        assertEquals(0, accountEntity.getNumeroContaCorrente());
        assertNull(accountEntity.getSaldoAtual());
        assertNull(accountEntity.getSucesso());
    }

    @Test
    public void testAccountEntitySettersAndGetters() {
        AccountEntity accountEntity = new AccountEntity();

        accountEntity.setCpfCliente("12345678900");
        accountEntity.setNumeroContaCorrente(1000);
        accountEntity.setSaldoAtual("5000.00");
        accountEntity.setSucesso("true");

        assertEquals("12345678900", accountEntity.getCpfCliente());
        assertEquals(1000, accountEntity.getNumeroContaCorrente());
        assertEquals("5000.00", accountEntity.getSaldoAtual());
        assertEquals("true", accountEntity.getSucesso());
    }
}
