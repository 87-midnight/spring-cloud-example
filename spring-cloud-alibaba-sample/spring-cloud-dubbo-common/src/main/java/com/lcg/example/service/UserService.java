package com.lcg.example.service;

import com.lcg.example.model.User;

import java.util.Collection;

public interface UserService {
    boolean save(User user);

    boolean remove(Long userId);

    Collection<User> findAll();
}
