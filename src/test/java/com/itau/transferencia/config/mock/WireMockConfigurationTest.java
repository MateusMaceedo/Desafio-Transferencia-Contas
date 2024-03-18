package com.itau.transferencia.config.mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WireMockConfigurationTest {
    private WireMockServer wireMockServer;
    private WebClient webClient;

    @BeforeEach
    void setUp() {
        wireMockServer = new WireMockServer();
        wireMockServer.start();
        configureFor("localhost", wireMockServer.port());
        webClient = WebClient.create("http://localhost:" + wireMockServer.port());
    }

    @AfterEach
    void tearDown() {
        wireMockServer.stop();
    }

    @Test
    void testGetRequest() {
        stubFor(get(urlEqualTo("/users"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody("{\"name\":\"Mateus Macedo\"}")));

        String response = webClient.get()
                .uri("/users")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        assertEquals("{\"name\":\"Mateus Macedo\"}", response);
    }

    @Test
    void testPostRequest() {
        stubFor(post(urlEqualTo("/bc"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("Success response body")));

        String response = webClient.post()
                .uri("/bc")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        assertEquals("Success response body", response);
    }

    @Test
    void testErrorPostRequest() {
        stubFor(post(urlEqualTo("/bc-error"))
                .willReturn(aResponse()
                        .withStatus(429)
                        .withBody("Error response body")));

        Mono<String> response = webClient.post()
                .uri("/bc-error")
                .retrieve()
                .bodyToMono(String.class);

        assertEquals("Error response body", response.block());
    }
}
