package com.lcg.sample;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


/**
 * webflux style
 */
@RestController
public class UserPropertyRefreshCtrl {

    private final Environment environment;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public UserPropertyRefreshCtrl(Environment environment, RabbitTemplate rabbitTemplate) {
        this.environment = environment;
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping(value = "/refresh")
    public Mono<UserProperty> refreshProperty(){
        UserProperty property = Binder.get(environment).bind("client.user",UserProperty.class).get();
        return Mono.justOrEmpty(property);
    }

    @GetMapping(value = "/publish")
    public Mono<ResponseEntity> publishRabbitMessage(@RequestParam String msg){
        this.rabbitTemplate.convertAndSend("test-mq",msg);
        return Mono.just(ResponseEntity.ok(HttpStatus.OK));
    }
}
