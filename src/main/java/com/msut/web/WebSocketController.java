package com.msut.web;

import com.msut.dto.MessageDto;
import com.msut.dto.NewMessageDto;
import com.msut.dto.UserDto;
import com.msut.service.MessageService;
import com.msut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

import static com.msut.config.Route.*;

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

    @SubscribeMapping(CHAT_USER)
    public List<UserDto> getAllUsers() {
        return userService.getAllLoggedUsers();
    }

    @MessageMapping(CHAT_MESSAGE)
    @SendTo(CHAT_MESSAGE)
    public MessageDto createMessage(@Valid @Payload NewMessageDto newMessageDto, Principal principal) {
        return messageService.createMessage(newMessageDto, userService.loadUserByUsername(principal.getName()));
    }

    @SubscribeMapping(CHAT_MESSAGE)
    public List<MessageDto> getAllMessages() {
        return messageService.getLatestMessages(0);
    }

    @MessageExceptionHandler
    @SendToUser(CHAT_ERROR)
    public String handleException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        return bindingResult.getFieldError().getDefaultMessage();
    }

}
