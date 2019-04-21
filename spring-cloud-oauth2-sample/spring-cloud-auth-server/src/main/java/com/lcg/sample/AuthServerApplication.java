package com.lcg.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;


@SpringCloudApplication
public class AuthServerApplication {

    public static void main(String[]args){
        SpringApplication.run(AuthServerApplication.class,args);
    }
}
