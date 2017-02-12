package com.msut.config;

/**
 * Created by mariusz on 12.02.17.
 */

public class Route {

    public static final String APP_PREFIX = "/app";
    public static final String LOGIN = "/chat.login";
    public static final String CHAT_LOGOUT = "/chat.logout";
    public static final String WEB_SOCKET = "/ws";
    public static final String[] BROKER_PREFIXES = {
            "/chat"
    };
    public static final String CHAT_USER = "/chat.users";
    public static final String CHAT_MESSAGE = "/chat.message";
    public static final String CHAT_ERROR = "/chat.error";
}
