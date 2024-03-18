package com.itau.transferencia.config.database;

import com.itau.transferencia.application.core.domain.dto.TransferAccountDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class DynamoDbRepositoryTest {
    @Mock
    private DynamoDbClient dynamoDbClient;

    @InjectMocks
    private DynamoDbRepository dynamoDbRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsertItem() {
        TransferAccountDTO dto = new TransferAccountDTO();
        dto.setId("123");
        dto.setIdentificadorBacenTransferencia("456");

        Map<String, String> expectedItemValues = new HashMap<>();
        expectedItemValues.put("id", dto.getId());
        expectedItemValues.put("destination", dto.getIdentificadorBacenTransferencia());

        dynamoDbRepository.insertItem("someKey", dto);

        verify(dynamoDbClient).putItem(any(PutItemRequest.class));
    }
}
