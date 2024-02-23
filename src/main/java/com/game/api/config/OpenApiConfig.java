package com.game.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        contact = @Contact(
                name ="Anaestasia",
                email = "anaestasia.mathieu@gmail.com",
                url = "https://github.com/anaestasia"
        ),
        description = "OpenApi documentation for Game API",
        title = "OpenAPI Specification",
        version = "1.0"

    ),
        servers = {
                        @Server(
                                description = "LOCAL ENV",
                                url = "http://localhost:8080"
                        ),
                        @Server(
                                description = "PROD ENV",
                                url = "http://localhost:8080" // TODO
                        )
                }

)
public class OpenApiConfig {

}
