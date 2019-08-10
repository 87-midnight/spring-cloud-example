package com.lcg.example.handler;

import com.lcg.example.model.User;
import com.lcg.example.redis.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class UserHandler {
    private final UserRepository Users;

    public UserHandler(UserRepository Users) {
        this.Users = Users;
    }

    public Mono<ServerResponse> all(ServerRequest req) {
        return ServerResponse.ok().body(this.Users.findAll(), User.class);
    }

    public Mono<ServerResponse> create(ServerRequest req) {
        return req.bodyToMono(User.class)
                .flatMap(User -> {
                    User.setId(UUID.randomUUID().toString());
                    User.setCreatedDate(LocalDateTime.now());
                    return this.Users.save(User);
                })
                .flatMap(p -> ServerResponse.created(URI.create("/Users/" + p.getId())).build());
    }

    public Mono<ServerResponse> get(ServerRequest req) {
        return this.Users.findById(req.pathVariable("id"))
                .flatMap(User -> ServerResponse.ok().body(Mono.just(User), User.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> update(ServerRequest req) {

        return Mono
                .zip(
                        (data) -> {
                            User p = (User) data[0];
                            User p2 = (User) data[1];
                            p.setUsername(p2.getUsername());
                            p.setGender(p2.getGender());
                            return p;
                        },
                        this.Users.findById(req.pathVariable("id")),
                        req.bodyToMono(User.class)
                )
                .cast(User.class)
                .flatMap(User -> this.Users.save(User))
                .flatMap(User -> ServerResponse.noContent().build());

    }

    public Mono<ServerResponse> delete(ServerRequest req) {
        return ServerResponse.noContent().build(this.Users.deleteById(req.pathVariable("id")));
    }
}
