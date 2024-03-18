package com.itau.transferencia.application.core.usecases.rules;

import com.itau.transferencia.application.core.domain.dto.AccountDTO;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContaAtivaRuleTest {
    @Test
    public void testCheckContaAtiva() {
        ContaAtivaRule contaAtivaRule = new ContaAtivaRule();

        AccountDTO account = new AccountDTO();
        account.setAtiva(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            contaAtivaRule.check(account, 100.0);
        });

        assertEquals("Conta corrente não está ativa", exception.getReason());
    }
}
