package com.lcg.example.consumer;


import com.alibaba.fastjson.JSON;
import com.lcg.example.model.CustomerPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;


@Slf4j
public class ConsumerService {

    @StreamListener("input-rabbit")
    public void handlerMsg(@Payload CustomerPayload payload){
        log.info("receive message from input channel:{}", JSON.toJSONString(payload));
    }

}
