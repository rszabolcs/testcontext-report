package com.example.testcachecontextbug;

import com.example.testcachecontextbug.services.Service01;
import com.example.testcachecontextbug.services.Service05;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.test.StepVerifier;

class Test04IT extends ServiceITBase {

    @Autowired
    Service01 serviceA;

    @SpyBean
    Service05 serviceB;

    @Test
    void case1() {
        StepVerifier
            .create(serviceA.getNumbers())
            .expectNext(1, 2, 3, 4, 5)
            .verifyComplete();

        Mockito.verifyNoInteractions(serviceB);
    }
}
