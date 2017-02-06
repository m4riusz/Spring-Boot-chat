package com.msut.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by mariusz on 06.02.17.
 */
public class Role implements GrantedAuthority {

    private String name;

    public Role(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
