package com.lcg.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

@Configuration
public class WebFluxConfig {

    @Bean
    public RouterFunction<ServerResponse> HelloWorld(){
        return RouterFunctions.route(RequestPredicates.GET("/hello")
                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), new HandlerFunction<ServerResponse>() {
            @Override
            public Mono<ServerResponse> handle(ServerRequest serverRequest) {
                return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                        .body(BodyInserters.fromObject("Hello, www.xttblog.com !"));
            }
        });
    }
}
