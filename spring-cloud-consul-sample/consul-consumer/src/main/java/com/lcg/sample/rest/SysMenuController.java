package com.lcg.sample.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linchuangang
 * @create 2019-08-01 15:50
 **/
@RestController
@RequestMapping(value = "/menu")
public class SysMenuController {

    @GetMapping(value = "/list")
    public Object list(){
        List<String> list = new ArrayList<>();
        list.add("home");
        list.add("central");
        list.add("profile");
        return list;
    }
}
