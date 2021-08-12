package com.aluraflix.aluraflix.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * Config file for Swagger/OpenApi. Only generate Swagger docs for the test environments.
 *
 * @author Murillo Pezzuol
 */
@Profile( { "local", "swagger" } )
@Configuration
public class SwaggerConfig
{

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
            .info(new Info().title("Aluraflix API")
                .description("API created for the Alura Challenge project.")
                .version("1.0.0"))
            .externalDocs(new ExternalDocumentation()
                .description("Murillo Pezzuol - Github")
                .url("https://github.com/mupezzuol/aluraflix"));
    }

}
