package com.example.demo.dto;

public class UserEntity {

    public int id;
    public String userName;
    public String password;
    public String email;

    public UserEntity() {

    }

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
