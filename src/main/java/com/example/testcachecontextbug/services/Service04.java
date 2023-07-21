package com.example.testcachecontextbug.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Service04 {

    private final Service03 serviceB;

    public Integer f(final Integer x) {
        return x + serviceB.f(x) + 1;
    }
}
