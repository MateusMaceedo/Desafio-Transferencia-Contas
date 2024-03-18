package com.itau.transferencia.config.services;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.itau.transferencia.adapters.outbound.repository.impl.AccountRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RepositoryConfigTest {
    @Test
    void testAccountRepositoryBeanCreation() {
        AmazonDynamoDB amazonDynamoDBMock = Mockito.mock(AmazonDynamoDB.class);
        RepositoryConfig repositoryConfig = new RepositoryConfig();

        AccountRepository accountRepository = repositoryConfig.accountRepository(amazonDynamoDBMock);

        assertNotNull(accountRepository);
    }
}
