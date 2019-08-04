package com.lcg.example.bus;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

public class UserRemoteApplicationEvent extends RemoteApplicationEvent {

    private User user;

    public UserRemoteApplicationEvent() {
    }

    public UserRemoteApplicationEvent(Object source, User user, String originService,
                                      String destinationService) {
        super(source, originService, destinationService);
        this.user = user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}