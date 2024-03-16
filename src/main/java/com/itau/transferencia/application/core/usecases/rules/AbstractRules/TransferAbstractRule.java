package com.itau.transferencia.application.core.usecases.rules.AbstractRules;

import com.itau.transferencia.application.core.domain.dto.AccountDTO;

public interface TransferAbstractRule {
    void check(AccountDTO account, double amount);
}
