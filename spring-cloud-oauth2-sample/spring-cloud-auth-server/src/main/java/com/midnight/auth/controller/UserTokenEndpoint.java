package com.midnight.auth.controller;

import com.midnight.auth.model.PlatformUser;
import com.midnight.auth.service.PlatformUserBaseService;
import com.midnight.common.result.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserTokenEndpoint {

    @Autowired
    private PlatformUserBaseService platformUserBaseService;

    @RequestMapping(value = "", produces = "application/json", method = RequestMethod.GET)
    public Map<String, Object> user(OAuth2Authentication user) {
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("user", user.getUserAuthentication().getPrincipal());
        userInfo.put("authorities", AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));
        return userInfo;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ApiResult register(@RequestBody PlatformUser user) {
        return ApiResult.success(platformUserBaseService.add(user));
    }
}
