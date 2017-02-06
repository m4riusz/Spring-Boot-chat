package com.msut.service;

import com.msut.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mariusz on 06.02.17.
 */
@Service
public interface UserService extends UserDetailsService {

    List<User> getAllUsers();
}
