package com.lcg.sample.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linchuangang
 * @create 2019-08-01 14:53
 **/
@RestController
public class ServiceController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/service/list")
    public Object queryList(){
        return  discoveryClient.getServices();
    }
}
