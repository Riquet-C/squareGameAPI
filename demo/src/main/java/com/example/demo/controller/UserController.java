package com.example.demo.controller;


import com.example.demo.dto.UserEntity;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserEntity createUser(@RequestBody UserEntity userEntity) {
        return userService.createUser(userEntity);
    }

    @GetMapping("/{userId}")
    public UserEntity getUser(@PathVariable int userId) {
        return userService.getUserById(userId);
    }

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{userId}")
    public UserEntity updateUser(@PathVariable int userId, @RequestBody UserEntity userEntity) {
        return userService.updateUser(userId, userEntity);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
    }
}
