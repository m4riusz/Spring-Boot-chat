package com.msut.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by mariusz on 06.02.17.
 */
public class Authority implements GrantedAuthority {

    private String name;

    public Authority(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
