package com.msut.mappers;

import com.msut.domain.Message;
import com.msut.dto.MessageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by mariusz on 10.02.17.
 */
@Mapper(uses = {UserMapper.class}, componentModel = "spring")
public interface MessageMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "createDate", target = "createDate")
    @Mapping(source = "owner", target = "creator")
    @Mapping(source = "text", target = "content")
    MessageDto messageToMessageDto(Message message);

    default String localDateTimeToString(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:SS"));
    }
}
