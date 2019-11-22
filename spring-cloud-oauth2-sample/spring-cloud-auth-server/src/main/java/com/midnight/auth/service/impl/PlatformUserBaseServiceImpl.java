package com.midnight.auth.service.impl;

import com.midnight.auth.model.PlatformUser;
import com.midnight.auth.repository.PlatformUserRepository;
import com.midnight.auth.service.PlatformUserBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PlatformUserBaseServiceImpl implements PlatformUserBaseService {

    @Autowired
    private PlatformUserRepository platformUserRepository;

    @Autowired
    private DelegatingPasswordEncoder delegatingPasswordEncoder;

    @Override
    public UUID add(PlatformUser platformUser) {
        if(platformUserRepository.findByUsername(platformUser.getUsername()) != null){
            throw new RuntimeException("用户名已经存在");
        }

        platformUser.setPassword(delegatingPasswordEncoder.encode(platformUser.getPassword()));
        platformUser.setDeleteFlag(0);
        platformUser.setEnableFlag(1);
        PlatformUser user = platformUserRepository.save(platformUser);

        return user.getPlatformUserId();
    }

    @Bean
    public DelegatingPasswordEncoder delegatingPasswordEncoder(){
        String encodingId = "bcrypt";
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put(encodingId, new BCryptPasswordEncoder());
        encoders.put("ldap", new LdapShaPasswordEncoder());
        encoders.put("MD4", new Md4PasswordEncoder());
        encoders.put("MD5", new MessageDigestPasswordEncoder("MD5"));
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());
        encoders.put("SHA-1", new MessageDigestPasswordEncoder("SHA-1"));
        encoders.put("SHA-256", new MessageDigestPasswordEncoder("SHA-256"));
        encoders.put("sha256", new StandardPasswordEncoder());

        return new DelegatingPasswordEncoder(encodingId, encoders);
    }
}
