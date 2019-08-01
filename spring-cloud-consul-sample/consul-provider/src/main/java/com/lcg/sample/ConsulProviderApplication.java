package com.lcg.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author linchuangang
 * @create 2019-08-01 12:02
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class ConsulProviderApplication {

    public static void main(String...args){
        SpringApplication.run(ConsulProviderApplication.class,args);
    }
}
