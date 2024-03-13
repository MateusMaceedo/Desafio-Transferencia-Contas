package com.itau.transferencia.config.database;

import com.itau.transferencia.application.core.domain.dto.TransferAccountDTO;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class DynamoDbRepository implements RepositoryData {

    private final DynamoDbClient dynamoDbClient;
    private static final String DYNAMO_TABLE = "tbt-transferencia-contas";
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(DynamoDbRepository.class);

    public DynamoDbRepository(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    @Override
    public void insertItem(String key, TransferAccountDTO dto) {
        Map<String, AttributeValue> itemValues = new HashMap<>();

        itemValues.put("id", AttributeValue.builder().s(dto.getId()).build());
        itemValues.put("destination", AttributeValue.builder().s(dto.getIdentificadorBacenTransferencia()).build());

        PutItemRequest request = PutItemRequest.builder()
                .tableName(DYNAMO_TABLE)
                .item(itemValues)
                .build();

        dynamoDbClient.putItem(request);
    }
}
