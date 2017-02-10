package com.msut.domain;

import java.time.LocalDateTime;

/**
 * Created by mariusz on 10.02.17.
 */
public class Message {

    private LocalDateTime createDate;
    private User owner;
    private String text;

    public Message(LocalDateTime createDate, User owner, String text) {
        this.createDate = createDate;
        this.owner = owner;
        this.text = text;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
