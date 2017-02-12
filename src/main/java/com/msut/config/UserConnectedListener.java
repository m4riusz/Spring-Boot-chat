package com.msut.config;

import com.msut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import static com.msut.config.Route.LOGIN;

/**
 * Created by mariusz on 12.02.17.
 */
@Component
public class UserConnectedListener implements ApplicationListener<SessionConnectedEvent> {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final UserService userService;

    @Autowired
    public UserConnectedListener(SimpMessagingTemplate simpMessagingTemplate, UserService userService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(SessionConnectedEvent sessionConnectedEvent) {
        String connectedUser = sessionConnectedEvent.getUser().getName();
        simpMessagingTemplate.convertAndSend(LOGIN, userService.userConnected(connectedUser));
    }
}
