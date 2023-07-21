package com.example.testcachecontextbug.test;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

import static org.awaitility.Awaitility.await;

public class WireMockInitializer {

    public static void waitForWireMock(final String wireMockPort) {
        WireMock.stubFor(
            WireMock.get("/").willReturn(WireMock.aResponse().withStatus(200))
        );

        final RestTemplate restTemplate = new RestTemplate();

        await()
            .atMost(Duration.ofSeconds(10))
            .ignoreExceptions()
            .until(
                () -> {
                    restTemplate.getForObject(
                        "http://localhost:" + wireMockPort + "/",
                        Void.class
                    );
                    return true;
                }
            );
    }
}
