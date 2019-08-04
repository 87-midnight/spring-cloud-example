package com.lcg.example.bus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.bus.event.AckRemoteApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublishController {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Value("${spring.cloud.bus.id}")
    private String originService;

    @Value("${server.port}")
    private int localServerPort;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Publish the {@link UserRemoteApplicationEvent}
     *
     * @param name the user name
     * @param destination the destination
     * @return If published
     */
    @GetMapping("/bus/event/publish/user")
    public boolean publish(@RequestParam String name,
                           @RequestParam(required = false) String destination) {
        User user = new User();
        user.setId(System.currentTimeMillis());
        user.setName(name);
        publisher.publishEvent(
                new UserRemoteApplicationEvent(this, user, originService, destination));
        return true;
    }

    /**
     * Listener on the {@link UserRemoteApplicationEvent}
     *
     * @param event {@link UserRemoteApplicationEvent}
     */
    @EventListener
    public void onEvent(UserRemoteApplicationEvent event) {
        System.out.printf("Server [port : %d] listeners on %s\n", localServerPort,
                event.getUser());
    }

    @EventListener
    public void onAckEvent(AckRemoteApplicationEvent event)
            throws JsonProcessingException {
        System.out.printf("Server [port : %d] listeners on %s\n", localServerPort,
                objectMapper.writeValueAsString(event));
    }
}
