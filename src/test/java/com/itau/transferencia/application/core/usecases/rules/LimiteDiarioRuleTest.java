package com.itau.transferencia.application.core.usecases.rules;

import com.itau.transferencia.application.core.domain.dto.AccountDTO;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LimiteDiarioRuleTest {
    @Test
    public void testCheckLimiteDiario() {
        LimiteDiarioRule limiteDiarioRule = new LimiteDiarioRule();

        AccountDTO account = new AccountDTO();
        account.setLimiteDisponivel(1000.0);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            limiteDiarioRule.check(account, 1500.0);
        });

        assertEquals("A transferência excedeu o limite diário de 1.000 reais", exception.getReason());
    }
}
