package com.lcg.example.redis;

import com.lcg.example.model.User;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class UserRepository {
    ReactiveRedisOperations<String, User> template;

    public UserRepository(ReactiveRedisOperations<String, User> template) {
        this.template = template;
    }

    public Flux<User> findAll() {
        return template.<String, User>opsForHash().values("Users");
    }

    public Mono<User> findById(String id) {
        return template.<String, User>opsForHash().get("Users", id);
    }

    public Mono<User> save(User User) {
        if (User.getId() != null) {
            String id = UUID.randomUUID().toString();
            User.setId(id);
        }
        return template.<String, User>opsForHash().put("Users", User.getId(), User)
                .log()
                .map(p -> User);

    }

    public Mono<Void> deleteById(String id) {
        return template.<String, User>opsForHash().remove("Users", id)
                .flatMap(p -> Mono.<Void>empty());
    }

    public Mono<Boolean> deleteAll() {
        return template.<String, User>opsForHash().delete("Users");
    }
}
