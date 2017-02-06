package com.msut.web;

import com.msut.domain.User;
import com.msut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.List;

/**
 * Created by mariusz on 05.02.17.
 */

@Controller
public class WebSocketController {

    private final UserService userService;

    @Autowired
    public WebSocketController(UserService userService) {
        this.userService = userService;
    }


    @MessageMapping("/users.new")
    @SendTo("/users.all")
    public User newLoggedUser(Principal principal) {
        return userService.loadUserByUsername(principal.getName());
    }

    @SubscribeMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

}
