package com.lcg.sample.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author linchuangang
 * @create 2019-08-01 16:15
 **/
@Configuration
public class CommonConfig {

    @Bean
    @ConfigurationProperties(prefix = "user")
    public UserProperties loadUser(){
        return new UserProperties();
    }
}
