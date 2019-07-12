package com.lcg.sample.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@ConditionalOnBean(ProducerStreamConfig.class)
@Component
@Slf4j
public class PublisherService {

    private final Source source;

    @Autowired
    public PublisherService(Source source) {
        this.source = source;
    }


    public void publishOrgChannel(){
        log.debug("Sending Kafka message ");
        CustomerMessage payload = new CustomerMessage();
        payload.setId(UUID.randomUUID().toString());
        payload.setTimestamp(System.currentTimeMillis());
        source.output().send(MessageBuilder.withPayload(payload).build());
    }
}
