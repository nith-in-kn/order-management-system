package com.usermanagement.userservice.service.impl;

import com.usermanagement.userservice.entity.User;
import com.usermanagement.userservice.repository.UserRepository;
import com.usermanagement.userservice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User findById(int id) {
        log.debug("findById with id: {}",id);
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        log.debug("save with id: {}",user);
        return userRepository.save(user);
    }
}
