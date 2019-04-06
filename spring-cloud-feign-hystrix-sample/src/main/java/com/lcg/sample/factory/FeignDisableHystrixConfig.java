package com.lcg.sample.factory;

import feign.Feign;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@Configurable
public class FeignDisableHystrixConfig {
    @Bean
    @Scope(value = "prototype")
    public Feign.Builder builder(){
        return Feign.builder();
    }
}
