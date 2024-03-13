package com.itau.transferencia.adapters.inbound.mapper.impl;

import com.itau.transferencia.adapters.inbound.entity.TransferAccountEntity;
import com.itau.transferencia.adapters.inbound.mapper.EntityMapper;
import com.itau.transferencia.application.core.domain.dto.TransferAccountDTO;

public class TransferToTransferEntityMapper implements EntityMapper<TransferAccountEntity, TransferAccountDTO> {

    @Override
    public TransferAccountDTO toDomain(TransferAccountEntity entity) {
        return null;
    }

    @Override
    public TransferAccountEntity toEntity(TransferAccountDTO domain) {
        return null;
    }
}
