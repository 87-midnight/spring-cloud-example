package com.lcg.sample.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "my-user-provider")
public interface UserProviderClient {

    @GetMapping(value = "/get")
    String getInfo(@RequestParam(value = "id") String id);
}
