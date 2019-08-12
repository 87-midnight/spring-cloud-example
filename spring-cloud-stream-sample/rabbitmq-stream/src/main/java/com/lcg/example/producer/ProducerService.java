package com.lcg.example.producer;

import com.lcg.example.model.CustomerPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;


@Slf4j
public class ProducerService {

    private final OutputChannel outputChannel;

    @Autowired
    public ProducerService(OutputChannel outputChannel) {
        this.outputChannel = outputChannel;
    }


    public void sendPayload(CustomerPayload msg){
        this.outputChannel.output1().send(MessageBuilder.withPayload(msg).build());
    }
}
