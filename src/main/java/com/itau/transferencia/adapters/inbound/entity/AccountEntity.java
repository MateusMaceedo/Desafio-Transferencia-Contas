package com.itau.transferencia.adapters.inbound.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@DynamoDBTable(tableName = "tbt-transferencia-contas")
public class AccountEntity {

    @DynamoDBHashKey(attributeName = "cpf_cliente")
    private String cpfCliente = null;

    @DynamoDBAttribute(attributeName = "numero_conta_corrente")
    private double numeroContaCorrente = 0;

    @DynamoDBAttribute(attributeName = "saldo_atual")
    private String saldoAtual = null;


    @DynamoDBAttribute(attributeName = "sucesso")
    private String sucesso = null;
}
