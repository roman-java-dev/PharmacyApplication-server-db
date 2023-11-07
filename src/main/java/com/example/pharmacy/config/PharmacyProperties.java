package com.example.pharmacy.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "pharmacy")
public record PharmacyProperties(
        String host,
        int port,
        ServiceProperties service) {
}
