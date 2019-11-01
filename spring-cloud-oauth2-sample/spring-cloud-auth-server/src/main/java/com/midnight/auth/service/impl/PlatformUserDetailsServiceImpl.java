package com.midnight.auth.service.impl;

import com.midnight.auth.model.PlatformUser;
import com.midnight.auth.repository.PlatformUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("platformUserDetailsServiceImpl")
public class PlatformUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    PlatformUserRepository platformUserRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        PlatformUser platformUser = platformUserRepository.loadUserByUsername(s);
        if(platformUser == null){
            throw new UsernameNotFoundException("Could not find " + s);
        }
        return new User(platformUser.getUsername(), platformUser.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
    }
}
