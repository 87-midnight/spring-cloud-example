package com.lcg.example.service;


import org.apache.dubbo.config.annotation.Service;

@Service
public class EchoServiceImpl implements EchoService{
    @Override
    public String echo(String message) {
        return "hello,I'm provider,"+message;
    }
}
