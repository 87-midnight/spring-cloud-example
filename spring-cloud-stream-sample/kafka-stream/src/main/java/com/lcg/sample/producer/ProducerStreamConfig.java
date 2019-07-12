package com.lcg.sample.producer;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;

@ConditionalOnProperty(prefix = "kafka.component",name = "id",havingValue = "producer")
@EnableBinding(Source.class)
@Configuration
public class ProducerStreamConfig {

    public interface SourceChannels {

        @Output("outboundOrg")
        MessageChannel outboundOrg();
    }
}
