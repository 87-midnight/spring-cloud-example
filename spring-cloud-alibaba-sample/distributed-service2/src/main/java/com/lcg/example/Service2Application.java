package com.lcg.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.alibaba.sentinel.annotation.SentinelRestTemplate;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

/**
 * @author linchuangang
 * @create 2019-07-30 17:21
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class Service2Application {

    public static void main(String[]args){
        SpringApplication.run(Service2Application.class,args);
    }
    @LoadBalanced
    @Bean(value = "r1")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @LoadBalanced
    @Bean(value = "r2")
    @SentinelRestTemplate
    public RestTemplate restTemplate1() {
        return new RestTemplate();
    }


    @FeignClient(name = "service-provider", fallback = EchoServiceFallback.class, configuration = FeignConfiguration.class)
    public interface EchoService {
        @RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
        String echo(@PathVariable("str") String str);

        @RequestMapping(value = "/divide", method = RequestMethod.GET)
        String divide(@RequestParam("a") Integer a, @RequestParam("b") Integer b);

        default String divide(Integer a) {
            return divide(a, 0);
        }

        @RequestMapping(value = "/notFound", method = RequestMethod.GET)
        String notFound();
    }
    class FeignConfiguration {
        @Bean
        public EchoServiceFallback echoServiceFallback() {
            return new EchoServiceFallback();
        }
    }

    class EchoServiceFallback implements Service2Application.EchoService {
        @Override
        public String echo(@PathVariable("str") String str) {
            return "echo fallback";
        }

        @Override
        public String divide(@RequestParam Integer a, @RequestParam Integer b) {
            return "divide fallback";
        }

        @Override
        public String notFound() {
            return "notFound fallback";
        }
    }
}
