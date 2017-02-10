package com.msut.service;

import com.msut.domain.User;
import com.msut.dto.MessageDto;
import com.msut.dto.NewMessageDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mariusz on 10.02.17.
 */
@Service
public interface MessageService {

    MessageDto createMessage(NewMessageDto newMessageDto, User creator);

    List<MessageDto> getLatestMessages(int pageNumber);
}
