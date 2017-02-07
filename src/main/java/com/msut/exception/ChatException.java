package com.msut.exception;

/**
 * Created by mariusz on 07.02.17.
 */
public abstract class ChatException extends RuntimeException {
    public ChatException() {
    }

    public ChatException(String message) {
        super(message);
    }
}
