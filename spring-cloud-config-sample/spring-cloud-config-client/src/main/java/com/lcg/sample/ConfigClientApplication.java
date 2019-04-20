package com.lcg.sample;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/**
 * @author linchuangang
 * @create 2019-04-09 16:15
 **/
@SpringBootApplication
@Slf4j
public class ConfigClientApplication {

    private final Environment environment;

    @Autowired
    public ConfigClientApplication(Environment environment) {
        this.environment = environment;
    }

    public static void main(String[]args){
        SpringApplication.run(ConfigClientApplication.class,args);
    }

    @Bean
    public ApplicationRunner check(){
        UserProperty property = Binder.get(environment).bind("client.user",UserProperty.class).get();
        return (args -> log.info("read properties from config server center:{}", JSON.toJSONString(property)));
    }
}
