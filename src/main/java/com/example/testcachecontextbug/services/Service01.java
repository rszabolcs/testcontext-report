package com.example.testcachecontextbug.services;

import com.example.testcachecontextbug.config.ApplicationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class Service01 {

    private final WebClient webClient;

    public Service01(
        ApplicationProperties applicationProperties,
        WebClient.Builder builder
    ) {
        webClient = builder
            .clone()
            .baseUrl(applicationProperties.getExternalServiceUrl())
            .build();
    }

    public Flux<Integer> getNumbers() {
        return webClient
            .get()
            .uri("/externalEndpoint")
            .retrieve()
            .bodyToFlux(Integer.class);
    }

    public Integer f(final Integer x) {
        return x + 1 ;
    }
}
