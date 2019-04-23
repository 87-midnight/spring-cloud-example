package com.lcg.sample.service;

import com.lcg.sample.model.SysUser;
import com.lcg.sample.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class SysUserService {

    private final UserRepository userRepository;

    public Long saveUser(SysUser user){
        return this.userRepository.save(user).getId();
    }

    public SysUser getByAccount(String account,String password){
        return this.userRepository.getByAccountAndPassword(account,password);
    }
}
