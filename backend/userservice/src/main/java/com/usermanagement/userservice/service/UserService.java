package com.usermanagement.userservice.service;

import com.usermanagement.userservice.entity.User;

import java.util.List;

public interface UserService {
    User findById(int id);
    List<User> findAll();
    User save(User user);
}
