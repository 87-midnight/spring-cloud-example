package com.lcg.example;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@EnableAutoConfiguration
public class Service3Application {
    public static void main(String[] args) {
        new SpringApplicationBuilder(Service3Application.class)
                .properties("spring.profiles.active=nacos").web(WebApplicationType.NONE)
                .run(args);
    }
}
