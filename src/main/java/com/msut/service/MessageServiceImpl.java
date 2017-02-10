package com.msut.service;

import com.msut.domain.Message;
import com.msut.domain.User;
import com.msut.dto.MessageDto;
import com.msut.dto.NewMessageDto;
import com.msut.mappers.MessageMapper;
import com.msut.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.data.domain.Sort.Direction.DESC;

/**
 * Created by mariusz on 10.02.17.
 */
@Service
public class MessageServiceImpl implements MessageService {

    public static final int DEFAULT_PAGE_SIZE = 20;
    private final MessageMapper messageMapper;
    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageMapper messageMapper, MessageRepository messageRepository) {
        this.messageMapper = messageMapper;
        this.messageRepository = messageRepository;
    }

    @Override
    public MessageDto createMessage(NewMessageDto newMessageDto, User creator) {
        Message message = new Message();
        message.setOwner(creator);
        message.setCreateDate(LocalDateTime.now());
        message.setText(newMessageDto.getContent());
        return messageMapper.messageToMessageDto(messageRepository.save(message));
    }

    @Override
    public List<MessageDto> getLatestMessages(int pageNumber) {
        PageRequest page = new PageRequest(pageNumber, DEFAULT_PAGE_SIZE, new Sort(DESC, "createDate"));
        return messageRepository.findAll(page).getContent()
                .stream().map(messageMapper::messageToMessageDto).collect(toList());
    }

}
