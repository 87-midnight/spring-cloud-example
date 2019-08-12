package com.lcg.example.config;

import com.lcg.example.consumer.ConsumerService;
import com.lcg.example.consumer.InputChannel;
import com.lcg.example.producer.OutputChannel;
import com.lcg.example.producer.ProducerService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadRabbitConfig {

    @Bean
    @StreamMessageConverter
    public MessageJsonConverter converter(){
        return new MessageJsonConverter();
    }

    // ------- producer config

    @Bean
    @ConditionalOnBean(value = RabbitProducerLoadParent.class)
    public ProducerService initializeProducer(OutputChannel outputChannel){
        return new ProducerService(outputChannel);
    }

    @ConditionalOnProperty(prefix = "rabbit.producer",name = "enable",havingValue = "true")
    @EnableBinding(OutputChannel.class)
    class RabbitProducerLoadParent{}
    // ------- producer config


    // ------- consumer config

    @Bean
    @ConditionalOnBean(value = RabbitConsumerLoadParent.class)
    public ConsumerService initializeConsumer(){
        return new ConsumerService();
    }

    @ConditionalOnProperty(prefix = "rabbit.consumer",name = "enable",havingValue = "true")
    @EnableBinding(InputChannel.class)
    class RabbitConsumerLoadParent{}
    // ------- consumer config
}
