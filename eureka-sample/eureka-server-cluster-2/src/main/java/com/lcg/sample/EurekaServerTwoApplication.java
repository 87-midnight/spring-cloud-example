package com.lcg.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableEurekaServer
@EnableWebSecurity
public class EurekaServerTwoApplication {

    public static void main(String[]args){
        SpringApplication.run(EurekaServerTwoApplication.class,args);
    }


    @Component
    public static class CsrfSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
            super.configure(http); // 这一句必须要加上的，否则直接关闭密码验证服务了
        }
    }
}
