package com.lcg.example;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.config.listener.Listener;
import com.lcg.example.mq.MySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @author linchuangang
 * @create 2019-07-25 18:30
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding({ MySource.class })
public class Service1Application implements ApplicationRunner{

    @Autowired
    private NacosConfigProperties nacosConfigProperties;

    public static void main(String[]args){
        SpringApplication.run(Service1Application.class,args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        nacosConfigProperties.configServiceInstance().addListener(
            "common", "DEFAULT_GROUP", new Listener() {

                /**
                 * Callback with latest config data.
                 *
                 * For example, config data in Nacos is:
                 *
                 * user.name=Nacos user.age=25
                 *
                 * @param configInfo latest config data for specific dataId in Nacos
                 * server
                 */
                @Override
                public void receiveConfigInfo(String configInfo) {
                    Properties properties = new Properties();
                    try {
                        properties.load(new StringReader(configInfo));
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("config changed: " + properties);
                }

                @Override
                public Executor getExecutor() {
                    return null;
                }
            });
    }
}
