package com.msut.service;

import com.msut.domain.User;
import com.msut.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mariusz on 06.02.17.
 */
@Service
public interface UserService extends UserDetailsService {

    @Override
    User loadUserByUsername(String username) throws UsernameNotFoundException;

    UserDto addNewUser(String username);

    UserDto removeUser(String username);

    List<UserDto> getAllUsers();

    List<UserDto> getAllLoggedUsers();
}
