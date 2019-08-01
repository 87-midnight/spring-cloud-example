package com.lcg.sample.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author linchuangang
 * @create 2019-08-01 15:53
 **/
@RestController
@RefreshScope
public class ConsumerController {

    //使用value注解的时候，需要加refreshScope注解
    @Value("${user.username:default}")
    private String username;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping(value = "/menuList")
    public Object list(){
        ServiceInstance instance = loadBalancerClient.choose("consul-consumer");
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(instance.getUri().toString()+"/menu/list",Object.class);
    }

    @GetMapping(value = "/test/refresh")
    public Object test(){
        return username;
    }
}
