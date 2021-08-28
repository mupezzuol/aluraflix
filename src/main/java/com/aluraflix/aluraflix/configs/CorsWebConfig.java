package com.aluraflix.aluraflix.configs;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import lombok.extern.slf4j.Slf4j;

/**
 * Configuration of the global CORS filter.
 *
 * @author Murillo Pezzuol.
 */
@Configuration
@Slf4j
public class CorsWebConfig {
    private static final String PATH = "/**";

    @Value("${cors.allowed.origins:*}")
    private String[] allowedOrigins;

    @Value("${cors.allowed.headers:Origin,Content-Type,Accept}")
    private String[] allowedHeaders;

    @Value("${cors.exposed.headers:Content-Length,Transfer-Encoding}")
    private String[] exposedHeaders;

    @Value("${cors.allowed.methods:GET,POST,HEAD,OPTIONS,PATCH,PUT,DELETE}")
    private String[] allowedMethods;

    @Value("${cors.allow.credentials:false}")
    private boolean allowCredentials;

    /**
     * Configure the global CORS filter.
     *
     * @return The configured CORS filter.
     */
    @Bean
    public CorsFilter pulseCorsFilter() {
        log.info("Setting up CORS Globally");

        final CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(Arrays.asList(allowedOrigins));
        config.setAllowedHeaders(Arrays.asList(allowedHeaders));
        config.setExposedHeaders(Arrays.asList(exposedHeaders));
        config.setAllowedMethods(Arrays.asList(allowedMethods));
        config.setAllowCredentials(allowCredentials);

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(PATH, config);

        return new CorsFilter(source);
    }

}
