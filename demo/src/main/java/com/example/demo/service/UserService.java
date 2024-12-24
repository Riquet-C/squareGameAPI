package com.example.demo.service;

import com.example.demo.dto.UserEntity;
import java.util.List;

public interface UserService {
    UserEntity createUser(UserEntity userEntity);

    UserEntity getUserById(int userId);

    List<UserEntity> getAllUsers();

    UserEntity updateUser(int userId, UserEntity userEntity);

    void deleteUser(int userId);

    UserEntity getUserByUsername(String username);
}
