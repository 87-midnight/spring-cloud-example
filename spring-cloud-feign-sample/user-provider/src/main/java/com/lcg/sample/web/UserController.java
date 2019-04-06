package com.lcg.sample.web;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {

    @GetMapping(value = "/get")
    public Object getInfo(@RequestParam String id){
        log.info("I'm a female.{}",id);
        JSONObject data = new JSONObject();
        data.put("id",id);
        data.put("timestamp",System.currentTimeMillis());
        return data;
    }
}
