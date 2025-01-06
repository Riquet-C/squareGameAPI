package com.example.demo.dao.inMemoryGame;

import com.example.demo.dto.TokensEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TokensDaoJpa extends JpaRepository<TokensEntity, UUID> {
}