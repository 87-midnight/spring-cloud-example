package com.lcg.sample.factory;

import com.alibaba.fastjson.JSONObject;
import com.lcg.sample.client.UserProviderClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserProviderClientFactory implements FallbackFactory<UserProviderClient> {
    @Override
    public UserProviderClient create(Throwable throwable) {
        return id -> {
            log.warn("feign fallback due to :{}",throwable);
            JSONObject user = new JSONObject();
            user.put("id",999L);
            user.put("username","default user");
            user.put("timestamp",System.currentTimeMillis());
            return user.toJSONString();
        };
    }
}
