package com.lcg.sample.client;

import com.lcg.sample.factory.UserProviderClientCallback;
import com.lcg.sample.factory.UserProviderClientFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "my-user-provider",fallback = UserProviderClientCallback.class,fallbackFactory = UserProviderClientFactory.class)
public interface UserProviderClient {

    @GetMapping(value = "/get")
    String getInfo(@RequestParam(value = "id") String id);
}
