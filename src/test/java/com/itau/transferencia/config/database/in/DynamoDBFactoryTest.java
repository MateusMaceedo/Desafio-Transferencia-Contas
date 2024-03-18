package com.itau.transferencia.config.database.in;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DynamoDBFactoryTest {
    @Mock
    private AmazonDynamoDB amazonDynamoDB;

    @InjectMocks
    private DynamoDBFactory dynamoDBFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAmazonDynamoDBBean() {
        assertNotNull(dynamoDBFactory.amazonDynamoDB());
    }
}
