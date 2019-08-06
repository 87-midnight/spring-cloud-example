package com.lcg.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@EnableAutoConfiguration
public class Service3Application {
    public static void main(String[] args) {
        SpringApplication.run(Service3Application.class,args);
    }

}
