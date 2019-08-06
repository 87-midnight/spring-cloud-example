package com.lcg.example.gateway;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

/**
 * <p>sentinel+gateway 限流配置类</p>
 * <p>外加配合添加nacos里的sentinel-gateway配置文件</p>
 * @author linchuangang
 * @create 2019-08-02 12:03
 **/
@Configuration
public class SentinelGatewayCustomerConfig {


    @Bean
    public BlockRequestHandler blockRequestHandler() {
        return (exchange, t) -> ServerResponse.status(444)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(fromObject("Sorry,SCS Sentinel block"));
    }

}
