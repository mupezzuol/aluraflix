package com.aluraflix.aluraflix.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

import java.time.Duration;
import java.util.Map;

import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

@RequiredArgsConstructor
@Configuration
public class CacheConfig {

    private final CacheServerProperties cacheServerProperties;

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> {
            for (Map.Entry<String, CacheServerProperties.CacheModel> cache : cacheServerProperties.getCaches().entrySet()) {
                builder.withCacheConfiguration(cache.getValue().getName(),
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(
                                Duration.ofMinutes(Long.parseLong(cache.getValue().getDurationInMinutes()))));
            }
        };
    }

    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(1))
                .disableCachingNullValues()
                .serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }

}
