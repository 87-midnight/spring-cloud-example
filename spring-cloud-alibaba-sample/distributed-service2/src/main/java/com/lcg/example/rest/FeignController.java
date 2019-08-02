package com.lcg.example.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linchuangang
 * @create 2019-08-02 11:01
 **/
@RestController
public class FeignController {

    @Autowired
    private FeignClientDemo feignClientDemo;

    @GetMapping(value = "/query/test")
    public Object queryTest(){
        return feignClientDemo.test();
    }


    @FeignClient("service1-provider")
    public interface FeignClientDemo{

        @GetMapping(value = "/test")
        String test();
    }
}
