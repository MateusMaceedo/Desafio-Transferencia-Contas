package com.itau.transferencia.application.core.usecases.rules;

import com.itau.transferencia.application.core.domain.dto.AccountDTO;
import com.itau.transferencia.application.core.usecases.rules.AbstractRules.TransferAbstractRule;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class LimiteDiarioRule implements TransferAbstractRule {
    @Override
    public void check(AccountDTO account, double amount) {
        if (amount > 1000) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A transferência excedeu o limite diário de 1.000 reais");
        }
    }
}
