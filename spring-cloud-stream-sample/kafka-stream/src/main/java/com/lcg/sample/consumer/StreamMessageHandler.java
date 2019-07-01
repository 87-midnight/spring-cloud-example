package com.lcg.sample.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.List;

@ConditionalOnBean(ConsumerKafkaConfig.class)
@Component
@Slf4j
public class StreamMessageHandler {

    @StreamListener("inboundOrgChanges")
    public void handle(List<Message<?>> message){
        log.info("message list size:{}",message.size());
        for (Message<?> msg : message){
            log.info("message payload:{}",msg.getPayload());
        }
    }
}
