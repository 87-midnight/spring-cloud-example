package com.lcg.example;

import com.alibaba.fastjson.JSON;
import com.lcg.example.model.CustomerPayload;
import com.lcg.example.producer.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class RabbitmqApplication {

    private final ApplicationContext context;

    public RabbitmqApplication(ApplicationContext context) {
        this.context = context;
    }


    public static void main(String...args){
        SpringApplication.run(RabbitmqApplication.class,args);
    }

    @Bean
    @ConditionalOnBean(value = ProducerService.class)
    public CommandLineRunner send(){
        return args -> {
            do {
                ProducerService producerService = context.getBean(ProducerService.class);
                CustomerPayload msg = CustomerPayload.builder().build();
                msg.setId(UUID.randomUUID().toString());
                msg.setPayload("I'm sending a character '" + new Random().nextInt(999) + "'");
                msg.setTime(new Date());
                log.info("first endpoint ready to send:{}", JSON.toJSONString(msg));
                producerService.sendPayload(msg);
                Thread.sleep(200);
            } while (true);
        };

    }
    @Bean
    @ConditionalOnBean(value = ProducerService.class)
    public CommandLineRunner sendTwo(){
        return args -> {
            do {
                ProducerService producerService = context.getBean(ProducerService.class);
                CustomerPayload msg = CustomerPayload.builder().build();
                msg.setId(UUID.randomUUID().toString());
                msg.setPayload("I'm second endpoint to send a character '" + new Random().nextInt(9999) + "'");
                msg.setTime(new Date());
                log.info("second endpoint ready to send:{}", JSON.toJSONString(msg));
                producerService.sendPayload(msg);
                Thread.sleep(200);
            } while (true);
        };

    }
}
