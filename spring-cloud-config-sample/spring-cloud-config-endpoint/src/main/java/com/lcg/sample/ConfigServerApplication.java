package com.lcg.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author linchuangang
 * @create 2019-04-09 16:25
 **/
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
    public static void main(String[]args){
        SpringApplication.run(ConfigServerApplication.class,args);
    }
}
