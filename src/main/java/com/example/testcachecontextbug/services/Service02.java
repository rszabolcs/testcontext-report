package com.example.testcachecontextbug.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Service02 {

    private final Service01 service01;

    public Integer f(final Integer x) {
        return x + service01.f(x) + 1;
    }
}
