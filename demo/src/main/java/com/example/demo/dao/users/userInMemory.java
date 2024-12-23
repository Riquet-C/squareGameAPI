package com.example.demo.dao.users;

import com.example.demo.dto.UserEntity;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class userInMemory implements UserDao{

    private final List<UserEntity> users = new ArrayList<>();
    private int currentId = 1;

    @Override
    public List<UserEntity> findAll() {
        return new ArrayList<>(users);
    }

    @Override
    public UserEntity findById(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public UserEntity add(UserEntity user) {
        user.setId(currentId++);
        users.add(user);
        return user;
    }

    @Override
    public UserEntity update(UserEntity user) {
        Optional<UserEntity> existingUser = users.stream()
                .filter(u -> u.getId() == user.getId())
                .findFirst();

        existingUser.ifPresent(u -> {
            u.setUserName(user.getUserName());
            u.setEmail(user.getEmail());
        });
        return user;
    }

    @Override
    public void delete(int id) {
        users.removeIf(user -> user.getId() == id);
    }
}
