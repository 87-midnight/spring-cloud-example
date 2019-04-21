package com.lcg.sample;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouterTestController {

    @GetMapping(value = "/get")
    public Object get(@RequestParam String id){
        return ResponseEntity.ok(id);
    }
}
