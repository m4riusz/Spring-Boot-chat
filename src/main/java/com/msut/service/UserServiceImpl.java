package com.msut.service;

import com.msut.domain.User;
import com.msut.dto.UserDto;
import com.msut.exception.UserAlreadyConnectedException;
import com.msut.mappers.UserMapper;
import com.msut.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.toList;

/**
 * Created by mariusz on 06.02.17.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Set<User> connectedUsers;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.connectedUsers = ConcurrentHashMap.newKeySet();
    }


    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::userToUserDto).collect(toList());
    }

    @Override
    public List<UserDto> getAllLoggedUsers() {
        return connectedUsers.stream().map(userMapper::userToUserDto).collect(toList());
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public UserDto userConnected(String username) {
        User newUser = loadUserByUsername(username);
        if (!connectedUsers.add(newUser)) {
            throw new UserAlreadyConnectedException(username);
        }
        return userMapper.userToUserDto(newUser);
    }

    @Override
    public UserDto userDisconnected(String username) {
        User user = loadUserByUsername(username);
        connectedUsers.remove(user);
        return userMapper.userToUserDto(user);
    }
}
