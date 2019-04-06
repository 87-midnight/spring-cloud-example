package com.lcg.sample.web;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping(value = "/get")
    public Object getInfo(@RequestParam String id){
        JSONObject data = new JSONObject();
        data.put("id",id);
        data.put("timestamp",System.currentTimeMillis());
        return data;
    }
}
