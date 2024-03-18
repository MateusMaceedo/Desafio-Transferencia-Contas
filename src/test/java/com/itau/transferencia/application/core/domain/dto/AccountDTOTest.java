package com.itau.transferencia.application.core.domain.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountDTOTest {
    @Test
    public void testGetLimiteDisponivel() {
        AccountDTO accountDTO = new AccountDTO();

        double limiteEsperado = 1000.0;
        accountDTO.setLimiteDisponivel(limiteEsperado);

        assertEquals(limiteEsperado, accountDTO.getLimiteDisponivel());
    }
}
