package com.example.testcachecontextbug;

import com.example.testcachecontextbug.test.WireMockInitializer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.common.Json;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;

@SpringBootTest(
    properties = {"application.external-service-url=http://localhost:${wiremock.server.port}"}
)
@AutoConfigureWireMock(port = 0)
class ServiceITBase {

    @Value("${wiremock.server.port}")
    String wireMockPort;

    @BeforeEach
    void init() {
        WireMockInitializer.waitForWireMock(wireMockPort);

        WireMock.stubFor(
            WireMock
                .get(WireMock.urlPathEqualTo("/externalEndpoint"))
                .willReturn(
                    ResponseDefinitionBuilder
                        .responseDefinition()
                        .withStatus(HttpStatus.OK.value())
                        .withBody(Json.write(List.of(1, 2, 3, 4, 5)))
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                )
        );
    }
}
