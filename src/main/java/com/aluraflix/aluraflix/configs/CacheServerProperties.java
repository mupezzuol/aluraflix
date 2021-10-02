package com.aluraflix.aluraflix.configs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "cache-properties")
public class CacheServerProperties {

    private Map<String, CacheModel> caches;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CacheModel {
        private String name;
        private String durationInMinutes;
    }
}