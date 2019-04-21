package com.lcg.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
public class AuthClientApplication {

    public static void main(String[]args){
        SpringApplication.run(AuthClientApplication.class,args);
    }
}
