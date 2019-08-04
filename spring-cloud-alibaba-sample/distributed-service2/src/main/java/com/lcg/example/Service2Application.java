package com.lcg.example;

import com.lcg.example.mq.MySink;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author linchuangang
 * @create 2019-07-30 17:21
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableBinding({ MySink.class })
@RemoteApplicationEventScan(basePackages = "com.lcg.example.bus")
public class Service2Application {

    public static void main(String[]args){
       new SpringApplicationBuilder().sources(Service2Application.class)
        .run(args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
