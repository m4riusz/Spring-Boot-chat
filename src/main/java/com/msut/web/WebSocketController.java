package com.msut.web;

import com.msut.dto.MessageDto;
import com.msut.dto.NewMessageDto;
import com.msut.dto.UserDto;
import com.msut.service.MessageService;
import com.msut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
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
    private final MessageService messageService;

    @Autowired
    public WebSocketController(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
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
        return userService.getAllLoggedUsers();
    }

    @MessageMapping("/chat.message.create")
    @SendTo("/chat.message.new")
    public MessageDto createMessage(@Payload NewMessageDto newMessageDto, Principal principal) {
        return messageService.createMessage(newMessageDto, userService.loadUserByUsername(principal.getName()));
    }

    @SubscribeMapping("/chat.message.all")
    public List<MessageDto> getAllMessages() {
        return messageService.getLatestMessages(0);
    }

}
