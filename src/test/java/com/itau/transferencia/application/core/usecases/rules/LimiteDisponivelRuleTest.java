package com.itau.transferencia.application.core.usecases.rules;

import com.itau.transferencia.application.core.domain.dto.AccountDTO;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LimiteDisponivelRuleTest {
    @Test
    public void testCheckLimiteDisponivel() {
        LimiteDisponivelRule limiteDisponivelRule = new LimiteDisponivelRule();

        AccountDTO account = new AccountDTO();
        account.setLimiteDisponivel(0);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            limiteDisponivelRule.check(account, 100.0);
        });

        assertEquals("Cliente não possui limite disponível para realizar a transferência", exception.getReason());
    }
}
