package com.msut.service;

import com.msut.domain.User;
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

    User addNewUser(String username);

    void removeUser(String username);

    List<User> getAllUsers();

    List<User> getAllLoggedUsers();
}
