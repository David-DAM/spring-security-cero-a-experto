package com.davinchicoder.spring_security_cero_a_experto.common.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Spring Boot Web API",
                version = "1.0.0",
                contact = @Contact(
                        name = "DavinchiCoder", email = "davinchicoder@gmail.com", url = "https://davinchicoder.dev"
                ),
                license = @License(
                        name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        servers = @Server(
                url = "http://localhost:8080",
                description = "Production"
        )
)
@Configuration
public class OpenAPIConfig {
}
