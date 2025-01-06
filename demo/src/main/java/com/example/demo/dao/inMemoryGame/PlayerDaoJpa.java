package com.example.demo.dao.inMemoryGame;

import com.example.demo.dto.PlayersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlayerDaoJpa extends JpaRepository<PlayersEntity, UUID> {
}
