package com.jpa.concepts.jpa_concepts.service;

import com.jpa.concepts.jpa_concepts.DTO.UserDto;
import com.jpa.concepts.jpa_concepts.Entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);
    User updateUser(int userId,User user);
    List<User> getAll();
    User get(int userId);
    void deleteUser(int userId);
}
