package com.davinchicoder.spring_security_cero_a_experto.IT.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;

import java.time.Duration;

@TestConfiguration
public class RestConfig {

    @Bean
    public TestRestTemplate getRestTemplate() {
        return new TestRestTemplate(new RestTemplateBuilder()
                .basicAuthentication("spring", "spring")
                .connectTimeout(Duration.ofSeconds(10))
        );
    }
}
