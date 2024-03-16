package com.itau.transferencia.application.core.usecases.rules;

import com.itau.transferencia.application.core.domain.dto.AccountDTO;
import com.itau.transferencia.application.core.usecases.rules.AbstractRules.TransferAbstractRule;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class LimiteDisponivelRule implements TransferAbstractRule {
    @Override
    public void check(AccountDTO account, double amount) {
        if (account.getLimiteDisponivel() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não possui limite disponível para realizar a transferência");
        }
    }
}
