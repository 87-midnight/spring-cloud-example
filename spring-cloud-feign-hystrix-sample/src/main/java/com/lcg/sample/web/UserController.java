package com.lcg.sample.web;

import com.lcg.sample.client.UserProviderClient;
import com.lcg.sample.client.UserProviderWithoutHystrixClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserProviderClient userProviderClient;
    @Autowired
    private  UserProviderWithoutHystrixClient userProviderWithoutHystrixClient;


    @GetMapping(value = "/query")
    public Object query(@RequestParam String id){
        return this.userProviderClient.getInfo(id);
    }

    @GetMapping(value = "/queryWithoutHystrix")
    public Object queryWithoutHystrix(@RequestParam String id){
        return this.userProviderWithoutHystrixClient.getUserInfo(id);
    }
}
