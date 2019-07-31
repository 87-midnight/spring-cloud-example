package com.lcg.example.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author linchuangang
 * @create 2019-07-30 18:38
 **/
@RefreshScope
@RestController
public class TestController {

    @Autowired
    private Environment environment;
    @GetMapping(value = "/test")
    public Object getMsg(){
        return environment.getProperty("my.user.username")+UUID.randomUUID().toString();
    }
}
