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

    @DynamoDBHashKey(attributeName = "cod_id")
    private String id = null;

    @DynamoDBAttribute(attributeName = "currency")
    private double currency = 0;
}
