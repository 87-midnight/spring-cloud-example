package com.lcg.sample.consumer;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

@ConditionalOnProperty(prefix = "kafka.component",name = "id",havingValue = "consumer")
public interface CustomChannels {
    @Input("inboundOrgChanges")
    SubscribableChannel orgs();
}