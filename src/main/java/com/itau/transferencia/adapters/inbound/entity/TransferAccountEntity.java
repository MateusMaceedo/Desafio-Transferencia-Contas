package com.itau.transferencia.adapters.inbound.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@DynamoDBDocument
public class TransferAccountEntity {

    @DynamoDBAttribute(attributeName = "cod_id_transfer")
    private String id;

    @DynamoDBAttribute(attributeName = "to_be_tranfered")
    private double toBeTranfered;

    @DynamoDBAttribute(attributeName = "destination")
    private String destination;

    @DynamoDBAttribute(attributeName = "sucesso")
    private String sucesso;

    @DynamoDBAttribute(attributeName = "ttl")
    private Long dataHoraExpiracaoDado;
}
