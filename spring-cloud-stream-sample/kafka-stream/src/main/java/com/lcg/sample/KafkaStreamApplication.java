package com.lcg.sample;

import com.lcg.sample.producer.PublisherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@Slf4j
@EnableKafkaStreams
@SpringBootApplication
public class KafkaStreamApplication implements ApplicationListener<ContextRefreshedEvent> {
    public static void main(String[]args){
        SpringApplication.run(KafkaStreamApplication.class,args);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.info("check result:{}",contextRefreshedEvent.getApplicationContext().containsBean("PublisherService"));
    }

    @Bean
    @ConditionalOnBean(PublisherService.class)
    public CommandLineRunner publish(){
        return (args)->{};
    }
}
