package com.lcg.example.consumer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface InputChannel {
    @Input("input-rabbit")
    SubscribableChannel input1();
}
