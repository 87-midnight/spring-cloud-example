package com.lcg.example.producer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OutputChannel {

    @Output("output-rabbit")
    MessageChannel output1();
}
