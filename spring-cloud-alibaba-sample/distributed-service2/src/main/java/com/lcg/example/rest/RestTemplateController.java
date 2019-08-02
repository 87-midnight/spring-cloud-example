package com.lcg.example.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author linchuangang
 * @create 2019-08-02 11:01
 **/
@RestController
public class RestTemplateController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping(value = "/get/test")
    public Object getTest(){
        //两种调用方式，第一种是使用restTemplate，已加上了loadBalanced注解。第二是使用loadBalancerClient来选择一个，在new
        //一个restTemplate来调用
        ServiceInstance instance = loadBalancerClient.choose("service1-provider");
        return new RestTemplate().getForEntity(instance.getUri().toString()+"/test",String.class);
//        return restTemplate.getForEntity("http://service1-provider/test",String.class);
    }
}
