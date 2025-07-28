package com.davinchicoder.spring_security_cero_a_experto.IT.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class RestConfig {

    @Value("${jwt.token}")
    private String token;

    @Bean
    public TestRestTemplate restTemplate() {
        return new TestRestTemplate(new RestTemplateBuilder()
                .defaultHeader("Authorization", "Bearer ".concat(token))
                .connectTimeout(Duration.ofSeconds(10))
                .rootUri("http://localhost:8080")
        );
    }
}
