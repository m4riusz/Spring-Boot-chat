package com.msut.exception;

/**
 * Created by mariusz on 07.02.17.
 */
public class UserAlreadyConnectedException extends ChatException {
    public UserAlreadyConnectedException() {
        super();
    }

    public UserAlreadyConnectedException(String message) {
        super(message);
    }
}
