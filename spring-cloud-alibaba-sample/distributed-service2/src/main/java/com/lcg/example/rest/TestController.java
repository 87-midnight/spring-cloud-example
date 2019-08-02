package com.lcg.example.rest;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author linchuangang
 * @create 2019-07-30 18:51
 **/
@RestController
public class TestController {

    /**
     * [
     {
     "resource": "/test",
     "limitApp": "default",
     "grade": 1,
     "count": 2,
     "strategy": 0,
     "controlBehavior": 0,
     "clusterMode": false
     }
     ] 在nacos中配置sentinel限流json，即可实现接口的限流
     * @return
     */
    @GetMapping(value = "/test")
    public Object getMsg(){
        return UUID.randomUUID().toString();
    }
}
