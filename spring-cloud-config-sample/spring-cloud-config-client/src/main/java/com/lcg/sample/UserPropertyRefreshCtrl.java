package com.lcg.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * webflux style
 */
@RestController
public class UserPropertyRefreshCtrl {

    private final Environment environment;

    @Autowired
    public UserPropertyRefreshCtrl(Environment environment) {
        this.environment = environment;
    }

    @GetMapping(value = "/refresh")
    public Mono<UserProperty> refreshProperty(){
        UserProperty property = Binder.get(environment).bind("client.user",UserProperty.class).get();
        return Mono.justOrEmpty(property);
    }
}
