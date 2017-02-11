package com.msut.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by mariusz on 10.02.17.
 */
public class NewMessageDto {

    @NotBlank(message = "Message can't be empty!")
    @Length(min = 1, max = 255,
            message = "Message length should be between {min} and {max}!")
    private String content;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
