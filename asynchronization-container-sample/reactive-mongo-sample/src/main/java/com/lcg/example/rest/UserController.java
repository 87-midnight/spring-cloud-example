package com.lcg.example.rest;

import com.lcg.example.model.User;
import com.lcg.example.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/save")
    public Mono<User> save(@RequestBody User user){
        return userRepository.save(user);
    }

    @GetMapping(value = "/list")
    public Flux<User> queryList(){
        return  userRepository.findAll();
    }
}
