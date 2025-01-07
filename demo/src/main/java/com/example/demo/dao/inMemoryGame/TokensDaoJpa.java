package com.example.demo.dao.inMemoryGame;

import com.example.demo.dto.TokensEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface TokensDaoJpa extends JpaRepository<TokensEntity, UUID> {
    @Query(value = "SELECT * FROM tokens_entity t WHERE t.token_name = :tokenName AND t.is_played = false LIMIT 1", nativeQuery = true)
    TokensEntity findFirstByTokenName(@Param("tokenName") String tokenName);

}