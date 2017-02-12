package com.msut.config;

import com.msut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * Created by mariusz on 12.02.17.
 */
@Component
public class UserDisconnectedListener implements ApplicationListener<SessionDisconnectEvent> {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final UserService userService;

    @Autowired
    public UserDisconnectedListener(SimpMessagingTemplate simpMessagingTemplate, UserService userService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(SessionDisconnectEvent sessionDisconnectEvent) {
        String disconnectedUser = sessionDisconnectEvent.getUser().getName();
        simpMessagingTemplate.convertAndSend("/chat.logout", userService.userDisconnected(disconnectedUser));
    }
}
