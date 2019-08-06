package com.lcg.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Service5Application {

    public static void main(String...args){
        SpringApplication.run(Service5Application.class,args);
    }
}
