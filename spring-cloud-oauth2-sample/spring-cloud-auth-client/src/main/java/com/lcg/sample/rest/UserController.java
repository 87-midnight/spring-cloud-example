package com.lcg.sample.rest;

import com.lcg.sample.model.SysUser;
import com.lcg.sample.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
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
}
