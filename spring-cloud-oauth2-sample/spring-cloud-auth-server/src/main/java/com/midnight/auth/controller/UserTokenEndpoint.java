package com.midnight.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(value = "/users")
public class UserTokenEndpoint {


    @GetMapping(value = "/get")
    public Principal getUser(Principal principal){
        return principal;
    }
}
