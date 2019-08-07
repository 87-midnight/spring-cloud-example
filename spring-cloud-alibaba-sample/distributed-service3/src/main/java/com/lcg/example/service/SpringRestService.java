package com.lcg.example.service;

import com.lcg.example.model.User;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Service
@RestController
public class SpringRestService implements RestService {
    @Override
    @GetMapping(value = "/param")
    public String param(@RequestParam String param) {
        return "dubbo rest test:"+param;
    }

    @Override
    public String params(int a, String b) {
        return null;
    }

    @Override
    public String headers(String header, String header2, Integer param) {
        return null;
    }

    @Override
    public String pathVariables(String path1, String path2, String param) {
        return null;
    }

    @Override
    public String form(String form) {
        return null;
    }

    @Override
    public User requestBodyMap(Map<String, Object> data, String param) {
        return null;
    }

    @Override
    public Map<String, Object> requestBodyUser(User user) {
        return null;
    }
}
