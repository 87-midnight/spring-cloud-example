package com.lcg.sample.web;

import com.lcg.sample.client.UserProviderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserProviderClient providerClient;

    @Autowired
    public UserController(UserProviderClient providerClient) {
        this.providerClient = providerClient;
    }

    @GetMapping(value = "/query")
    public Object query(@RequestParam String id){
        return this.providerClient.getInfo(id);
    }
}
