package com.example.demo.dao.users;

import com.example.demo.dto.UserEntity;
import java.util.List;

public interface UserDao {
    List<UserEntity> findAll();
    UserEntity findById(int id);
    UserEntity add(UserEntity user);
    UserEntity update(UserEntity user);
    void delete(int id);
}