package com.lcg.sample.rest;

import com.lcg.sample.model.SysUser;
import com.lcg.sample.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final SysUserService sysUserService;

    @Autowired
    public UserController(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @PostMapping(value = "/save")
    public Object saveUser(@RequestBody SysUser user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword("{bcrypt}"+encoder.encode(user.getPassword()));
        return this.sysUserService.saveUser(user);
    }

    @GetMapping(value = "/list")
    public Object list(){
        return this.sysUserService.queryList();
    }

    @GetMapping("/getPrinciple")
    public OAuth2Authentication getPrinciple(OAuth2Authentication oAuth2Authentication, Principal principal, Authentication authentication) {
        log.info(oAuth2Authentication.getUserAuthentication().getAuthorities().toString());
        log.info(oAuth2Authentication.toString());
        log.info("principal.toString() " + principal.toString());
        log.info("principal.getName() " + principal.getName());
        log.info("authentication: " + authentication.getAuthorities().toString());

        return oAuth2Authentication;
    }
}
