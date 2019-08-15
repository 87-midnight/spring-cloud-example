package com.lcg.sample.service;

import com.lcg.sample.model.SysUser;
import com.lcg.sample.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class SysUserService implements UserDetailsService {

    private final UserRepository userRepository;

    public Long saveUser(SysUser user){
        return this.userRepository.save(user).getId();
    }

    public List<SysUser> queryList(){
        return this.userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username);
    }
}
