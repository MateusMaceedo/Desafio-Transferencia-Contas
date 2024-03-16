package com.itau.transferencia.adapters.inbound.mapper.impl;

import com.itau.transferencia.adapters.inbound.entity.TransferAccountEntity;
import com.itau.transferencia.adapters.inbound.mapper.EntityMapper;
import com.itau.transferencia.application.core.domain.dto.TransferAccountDTO;
import java.util.Date;

public class TransferToTransferEntityMapper implements EntityMapper<TransferAccountEntity, TransferAccountDTO> {

    @Override
    public TransferAccountDTO toDomain(TransferAccountEntity entity) {

        if (entity == null) {
            return null;
        }

        TransferAccountDTO transferAccountDTO = new TransferAccountDTO();
        transferAccountDTO.setId(entity.getId());
        transferAccountDTO.setCpfCliente(entity.getDestination());
        transferAccountDTO.setValorTransferencia(String.valueOf(entity.getToBeTranfered()));
        transferAccountDTO.setDataTransferencia(new Date());
        transferAccountDTO.setIdentificadorBacenTransferencia(null);
        transferAccountDTO.setNumeroContaDestino(null);
        transferAccountDTO.setSucesso(entity.getSucesso());
        transferAccountDTO.setDataHoraExpiracaoDado(entity.getDataHoraExpiracaoDado());

        return transferAccountDTO;
    }

    @Override
    public TransferAccountEntity toEntity(TransferAccountDTO domain) {

        if (domain == null) {
            return null;
        }

        TransferAccountEntity transferAccountEntity = new TransferAccountEntity();

        transferAccountEntity.setId(domain.getId());
        transferAccountEntity.setToBeTranfered(Double.parseDouble(domain.getValorTransferencia()));
        transferAccountEntity.setDestination(domain.getCpfCliente());
        transferAccountEntity.setSucesso(domain.getSucesso());
        transferAccountEntity.setDataHoraExpiracaoDado(domain.getDataHoraExpiracaoDado());

        return transferAccountEntity;
    }
}
