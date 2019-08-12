package com.lcg.example.config;

import com.alibaba.fastjson.JSON;
import com.lcg.example.model.CustomerPayload;
import org.springframework.messaging.Message;
import org.springframework.messaging.converter.AbstractMessageConverter;
import org.springframework.util.MimeType;

public class MessageJsonConverter extends AbstractMessageConverter {
    protected MessageJsonConverter() {
        super(new MimeType("application","json"));
    }

    @Override
    protected boolean supports(Class<?> aClass) {
        return CustomerPayload.class == aClass;
    }

    @Override
    protected Object convertFromInternal(Message<?> message, Class<?> targetClass, Object conversionHint) {
        Object payload = message.getPayload();
        return (payload instanceof CustomerPayload ? payload : JSON.parseObject((byte[]) payload,CustomerPayload.class));
    }
}
