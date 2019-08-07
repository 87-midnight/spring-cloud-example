package com.lcg.example;

import com.alibaba.cloud.dubbo.annotation.DubboTransported;
import com.lcg.example.service.EchoService;
import com.lcg.example.service.RestService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@EnableAutoConfiguration
@RestController
@EnableScheduling
@Slf4j
@EnableFeignClients
public class Service4Application {
    @Reference
    private EchoService echoService;
    @Autowired
    @Lazy
    private RestTemplate restTemplate;

    @Reference
    private RestService restService;
    @Autowired
    @Lazy
    private DubboFeignRestService dubboFeignRestService;
    @Autowired
    @Lazy
    private FeignRestService feignRestService;

    @GetMapping("/echo")
    public String echo(String message) {
        return echoService.echo(message);
    }

    public static void main(String[] args) {
        SpringApplication.run(Service4Application.class,args);
    }

    @FeignClient("service3-cloud-dubbo-provider")
    public interface FeignRestService{

        @GetMapping(value = "/param")
        String getParam(@RequestParam("param") String param);
    }

    @FeignClient("service3-cloud-dubbo-provider")
    @DubboTransported
    public interface DubboFeignRestService{

        @GetMapping(value = "/param")
        String getParam(@RequestParam("param") String param);
    }
    @Bean
    @LoadBalanced
    @DubboTransported
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Scheduled(fixedDelay = 2000)
    public void schedule(){
       callTwoRestFulWay();
    }

    private void callTwoRestFulWay(){
        String url = "http://service3-cloud-dubbo-provider/param?param=2222";

        log.info("dubbo original 结果集：{}",restService.param("dubbo original"));

        log.info("restTemplate 结果集：{}",restTemplate.getForEntity(url,String.class).getBody());

        log.info("dubbo feign 结果集：{}",dubboFeignRestService.getParam("mine yep"));

        log.info("feign结果集：{}",feignRestService.getParam("feign welldone"));
    }
}
