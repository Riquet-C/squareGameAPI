package com.example.demo.service;

import com.example.demo.dao.users.UserDao;
import com.example.demo.dto.UserEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private final List<UserEntity> users;

    public UserServiceImpl(List<UserEntity> users, UserDao userDao) {
        this.users = users;
        this.userDao = userDao;
    }

    @Override
    public UserEntity createUser(UserEntity userEntity) {
        return userDao.add(userEntity);
    }

    @Override
    public UserEntity getUserById(int userId) {
        return userDao.findById(userId);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public UserEntity updateUser(int userId, UserEntity userEntity) {
        return userDao.update(userEntity);
    }

    @Override
    public void deleteUser(int userId) {
        userDao.delete(userId);
    }
}
