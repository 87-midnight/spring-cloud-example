package com.lcg.sample.client;

import com.lcg.sample.factory.FeignDisableHystrixConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "my-user-provider-1",configuration = FeignDisableHystrixConfig.class)
public interface UserProviderWithoutHystrixClient {

    @GetMapping(value = "/get")
    String getUserInfo(@RequestParam(value = "id") String id);
}
