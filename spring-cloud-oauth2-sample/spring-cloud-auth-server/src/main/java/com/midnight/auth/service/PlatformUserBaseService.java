package com.midnight.auth.service;

import com.midnight.auth.model.PlatformUser;

import java.util.UUID;

public interface PlatformUserBaseService {

    public UUID add(PlatformUser platformUser);

}
