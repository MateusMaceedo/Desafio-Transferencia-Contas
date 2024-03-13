package com.itau.transferencia.adapters.inbound.mapper.impl;

import com.itau.transferencia.adapters.inbound.entity.AccountEntity;
import com.itau.transferencia.adapters.inbound.mapper.EntityMapper;
import com.itau.transferencia.application.core.domain.dto.AccountDTO;

public class AccountToAccountEntityMapper implements EntityMapper<AccountEntity, AccountDTO> {

    @Override
    public AccountDTO toDomain(AccountEntity entity) {
        return null;
    }

    @Override
    public AccountEntity toEntity(AccountDTO domain) {
        return null;
    }
}
