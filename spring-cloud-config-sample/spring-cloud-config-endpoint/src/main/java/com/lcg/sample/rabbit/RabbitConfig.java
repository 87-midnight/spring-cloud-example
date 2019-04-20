package com.lcg.sample.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue queue(){
        return new Queue("test-mq");
    }

    @Component
    @RabbitListener(queues = "test-mq")
    @Slf4j
    public static class RabbitMessageReceive{

        @RabbitHandler
        public void print(String msg)throws Exception{
            log.info("receive rabbit mq message :{}", msg);
        }
    }
}
