package com.lcg.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author linchuangang
 * @create 2019-07-25 18:30
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class Service1Application {

    public static void main(String[]args){
        SpringApplication.run(Service1Application.class,args);
    }
}
