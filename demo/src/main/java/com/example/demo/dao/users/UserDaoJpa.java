package com.example.demo.dao.users;

import com.example.demo.dto.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDaoJpa extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUserName(String userName);
}
