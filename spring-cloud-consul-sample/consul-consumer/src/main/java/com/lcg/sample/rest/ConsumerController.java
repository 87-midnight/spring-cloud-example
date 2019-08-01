package com.lcg.sample.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author linchuangang
 * @create 2019-08-01 14:57
 **/
@RestController
public class ConsumerController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping(value = "/user/get")
    public Object getInfo(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("consul-provider");
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(serviceInstance.getUri().toString()+"/user/get?id=123",Object.class);
    }
}
