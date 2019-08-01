package com.lcg.sample.rest;

import com.alibaba.fastjson.JSON;
import com.lcg.sample.config.UserProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author linchuangang
 * @create 2019-08-01 15:29
 **/
@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserController {

    @Autowired
    private UserProperties properties;

    @GetMapping(value = "/get")
    public Object getUserInfo(@RequestParam String id){
        Map<String,String> user = new HashMap<>();
        user.put("id",id);
        user.put("name","provider");
        log.info("i'm doing job");
        return user;
    }

    @GetMapping(value = "/test")
    public Object refreshProperties(){
        return JSON.toJSONString(properties);
    }
}
