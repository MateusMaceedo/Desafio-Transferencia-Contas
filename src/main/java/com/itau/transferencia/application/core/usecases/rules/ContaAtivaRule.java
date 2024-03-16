package com.itau.transferencia.application.core.usecases.rules;

import com.itau.transferencia.application.core.domain.dto.AccountDTO;
import com.itau.transferencia.application.core.usecases.rules.AbstractRules.TransferAbstractRule;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ContaAtivaRule implements TransferAbstractRule {
    @Override
    public void check(AccountDTO account, double amount) {
        if (!account.isAtiva()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Conta corrente não está ativa");
        }
    }
}
