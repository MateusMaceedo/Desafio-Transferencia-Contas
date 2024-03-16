package com.itau.transferencia.adapters.inbound.mapper.impl;

import com.itau.transferencia.adapters.inbound.entity.AccountEntity;
import com.itau.transferencia.adapters.inbound.mapper.EntityMapper;
import com.itau.transferencia.application.core.domain.dto.AccountDTO;

public class AccountToAccountEntityMapper implements EntityMapper<AccountEntity, AccountDTO> {

    @Override
    public AccountDTO toDomain(AccountEntity entity) {
        AccountDTO accountDTO = new AccountDTO();

        if (entity == null) {
            return null;
        }

        accountDTO.setCpfCliente(entity.getCpfCliente());
        accountDTO.setNumeroContaCorrente(String.valueOf(entity.getNumeroContaCorrente()));
        accountDTO.setSaldoAtual(entity.getSaldoAtual());
        accountDTO.setSucesso(entity.getSucesso());

        return accountDTO;
    }

    @Override
    public AccountEntity toEntity(AccountDTO domain) {
        if (domain == null) {
            return null;
        }

        AccountEntity accountEntity = new AccountEntity();

        accountEntity.setCpfCliente(domain.getCpfCliente());
        accountEntity.setNumeroContaCorrente(Double.parseDouble(domain.getNumeroContaCorrente()));
        accountEntity.setSaldoAtual(domain.getSaldoAtual());
        accountEntity.setSucesso(domain.getSucesso());

        return accountEntity;
    }
}
