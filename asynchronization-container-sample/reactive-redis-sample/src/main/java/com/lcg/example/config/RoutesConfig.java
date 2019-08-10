package com.lcg.example.config;

import com.lcg.example.handler.UserHandler;
import com.lcg.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;


import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RoutesConfig {

    private final ReactiveRedisTemplate<String,User> reactiveRedisTemplate;

    @Autowired
    public RoutesConfig(ReactiveRedisTemplate<String,User> reactiveRedisTemplate) {
        this.reactiveRedisTemplate = reactiveRedisTemplate;
    }


    @Bean
    public RouterFunction<ServerResponse> userController(UserHandler handler){
        return route(GET("/users"), handler::all)
                .andRoute(POST("/users"), handler::create)
                .andRoute(GET("/users/{id}"), handler::get)
                .andRoute(PUT("/users/{id}"), handler::update)
                .andRoute(DELETE("/users/{id}"), handler::delete);
    }
}
