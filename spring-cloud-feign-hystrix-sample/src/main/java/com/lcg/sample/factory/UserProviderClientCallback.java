package com.lcg.sample.factory;

import com.alibaba.fastjson.JSONObject;
import com.lcg.sample.client.UserProviderClient;
import org.springframework.stereotype.Component;

@Component
public class UserProviderClientCallback implements UserProviderClient {
    @Override
    public String getInfo(String id) {
        JSONObject user = new JSONObject();
        user.put("id",888L);
        user.put("username","default user");
        user.put("timestamp",System.currentTimeMillis());
        return user.toJSONString();
    }
}
