package com.msut.service;

import com.msut.domain.User;
import com.msut.exception.UserAlreadyConnectedException;
import com.msut.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by mariusz on 06.02.17.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Set<User> connectedUsers;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.connectedUsers = new HashSet<>();
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getAllLoggedUsers() {
        return new ArrayList<>(connectedUsers);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public User addNewUser(String username) {
        User newUser = loadUserByUsername(username);
        if (!connectedUsers.add(newUser)) {
            throw new UserAlreadyConnectedException(username);
        }
        return newUser;
    }

    @Override
    public void removeUser(String username) {
        connectedUsers.remove(loadUserByUsername(username));
    }
}
