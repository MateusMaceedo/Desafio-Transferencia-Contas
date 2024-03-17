package com.itau.transferencia.adapters.outbound.repository.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.itau.transferencia.adapters.inbound.entity.AccountEntity;
import com.itau.transferencia.adapters.inbound.entity.TransferAccountEntity;
import com.itau.transferencia.adapters.inbound.mapper.impl.AccountToAccountEntityMapper;
import com.itau.transferencia.adapters.inbound.mapper.impl.TransferToTransferEntityMapper;
import com.itau.transferencia.application.core.domain.dto.AccountDTO;
import com.itau.transferencia.application.core.domain.dto.TransferAccountDTO;
import reactor.core.publisher.Mono;

public class AccountRepository {

    private final DynamoDBMapper dynamoDBMapper;
    private final AccountToAccountEntityMapper accountToAccountEntityMapper;
    private final AccountToAccountEntityMapper accountEntityToAccountMapper;
    private final TransferToTransferEntityMapper transferToTransferEntityMapper;


    public AccountRepository(AmazonDynamoDB amazonDynamoDB) {
        this.dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
        this.accountToAccountEntityMapper = new AccountToAccountEntityMapper();
        this.accountEntityToAccountMapper = new AccountToAccountEntityMapper();
        this.transferToTransferEntityMapper = new TransferToTransferEntityMapper();
    }

    public Mono<AccountDTO> save(AccountDTO accountDTO)  {
        AccountEntity accountEntity = accountToAccountEntityMapper.toEntity(accountDTO);
        dynamoDBMapper.save(accountEntity);
        return Mono.just(accountDTO);
    }

    public Mono<AccountDTO> findById(String cpfCliente) {
        AccountEntity accountEntity = dynamoDBMapper.load(AccountEntity.class, cpfCliente);
        return Mono.justOrEmpty(accountEntityToAccountMapper.toDomain(accountEntity));
    }

    public void saveTransfer(TransferAccountDTO transferDTO) {
        TransferAccountEntity transferEntity = transferToTransferEntityMapper.toEntity(transferDTO);
        dynamoDBMapper.save(transferEntity);
    }

    public TransferAccountDTO findTransferById(String id) {
        TransferAccountEntity transferEntity = dynamoDBMapper.load(TransferAccountEntity.class, id);
        return transferToTransferEntityMapper.toDomain(transferEntity);
    }
}
