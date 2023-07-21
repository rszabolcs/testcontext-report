package com.example.testcachecontextbug;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.example.testcachecontextbug.config")
public class TestCacheContextBugApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestCacheContextBugApplication.class, args);
    }

}
