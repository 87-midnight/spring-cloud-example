package com.lcg.sample.consumer;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@ConditionalOnProperty(prefix = "kafka.component",name = "id",havingValue = "consumer")
@Configuration
@EnableBinding(CustomChannels.class)
public class ConsumerKafkaConfig {

}
