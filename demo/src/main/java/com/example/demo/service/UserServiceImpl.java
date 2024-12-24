package com.example.demo.service;

import com.example.demo.dao.users.UserDaoJpa;
import com.example.demo.dto.UserEntity;
import com.example.demo.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDaoJpa userDao;
    private final List<UserEntity> users;

    public UserServiceImpl(List<UserEntity> users, UserDaoJpa userDao) {
        this.users = users;
        this.userDao = userDao;
    }

    @Override
    public UserEntity createUser(UserEntity userEntity) {
        return userDao.save(userEntity);
    }

    @Override
    public UserEntity getUserById(int userId) {
        return userDao.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public UserEntity updateUser(int userId, UserEntity userEntity) {
        return userDao.findById(userId)
                .map(user -> {
                    user.setUserName(userEntity.getUserName());
                    user.setEmail(userEntity.getEmail());
                    user.setPassword(userEntity.getPassword());
                    return userDao.save(user);
                })
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));
    }

    @Override
    public void deleteUser(int userId) {
        userDao.deleteById(userId);
    }

    @Override
    public UserEntity getUserByUsername(String userName) {
        UserEntity user = userDao.findByUserName(userName);
        if (user == null) {
            throw new UserNotFoundException("User with name " + userName + " not found");
        }
        return user;
    }
}
