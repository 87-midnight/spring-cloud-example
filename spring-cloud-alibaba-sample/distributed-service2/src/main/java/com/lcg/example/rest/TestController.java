package com.lcg.example.rest;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author linchuangang
 * @create 2019-07-30 18:51
 **/
@RestController
public class TestController {

    @GetMapping(value = "/test")
    public Object getMsg(){
        return UUID.randomUUID().toString();
    }
}
