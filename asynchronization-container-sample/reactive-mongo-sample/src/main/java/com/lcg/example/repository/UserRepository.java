package com.lcg.example.repository;

import com.lcg.example.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository extends ReactiveMongoRepository<User,Long> {
}
