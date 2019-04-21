package com.lcg.sample.router;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfig {

    @Bean
    public RouteLocator define(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r -> r.path("/test/**").and().query("id")
                        .filters(f->f.stripPrefix(1)).uri("http://localhost:9002")
                )
                .build();
    }
}
