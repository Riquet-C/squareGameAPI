package com.example.demo.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    @Column(nullable = false)
    @NotBlank(message = "User name cannot be blank")
    public String userName;

    @Column(nullable = false)
    @NotBlank(message = "Password cannot be blank")
    public String password;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    public String email;

    public int getId() {
        return id;
    }
    public void setId(int i) {
        id = i;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getUserName() {
        return userName;
    }


}
