package com.msut.web;

import com.msut.dto.UserDto;
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


    @MessageMapping("/chat.login")
    @SendTo("/chat.login")
    public UserDto connectToServer(Principal principal) {
        return userService.addNewUser(principal.getName());
    }

    @MessageMapping("/chat.logout")
    @SendTo("/chat.logout")
    public UserDto disconnectFromServer(Principal principal) {
        return userService.removeUser(principal.getName());
    }

    @SubscribeMapping("/chat.users")
    public List<UserDto> getAllUsers() {
        System.out.println(userService.getAllLoggedUsers());
        return userService.getAllLoggedUsers();
    }


}
