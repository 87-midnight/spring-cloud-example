package com.midnight.auth.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
@ConfigurationProperties(prefix = "service.config", ignoreUnknownFields = false)
@Getter
@Setter
public class ServiceConfig {
    private String jwtSigningKey;
}
