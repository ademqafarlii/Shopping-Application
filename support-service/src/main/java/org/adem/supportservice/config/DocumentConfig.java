package org.adem.supportservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class DocumentConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(
                new Info()
                        .title("Shopping Application")
                        .version("0.1.0")
                        .description("An intuitive shopping application for a seamless and enjoyable retail experience.")
                        .contact(new Contact()
                                .email("ademqafarli88@gmail.com")
                                .url("www.linkedin.com/in/adem-qafarli-653913295")
                                .url("https://gitlab.com/ademqafarli77")
                                .url("https://github.com/ademqafarlii")
                                .name("Adem Qafarli"))
        );
    }
}
