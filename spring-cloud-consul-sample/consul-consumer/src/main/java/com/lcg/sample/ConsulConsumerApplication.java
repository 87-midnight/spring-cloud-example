package com.lcg.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author linchuangang
 * @create 2019-08-01 11:59
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class ConsulConsumerApplication {

    public static void main(String...args){
        SpringApplication.run(ConsulConsumerApplication.class,args);
    }
}
