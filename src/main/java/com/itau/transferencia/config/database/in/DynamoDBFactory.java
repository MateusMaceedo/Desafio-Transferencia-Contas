package com.itau.transferencia.config.database.in;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.inject.Singleton;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import java.net.URI;

@Configuration
public class DynamoDBFactory {

    @Value("${aws.endpoints.dynamodb.endpointOverride}") String uri;

    @Bean
    @Singleton
    public DynamoDbClient amazonDynamoDB() {
        return DynamoDbClient.builder()
                .endpointOverride(URI.create(uri))
                .region(Region.SA_EAST_1)
                .build();
    }
}
