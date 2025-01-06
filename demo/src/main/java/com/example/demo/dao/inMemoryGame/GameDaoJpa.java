package com.example.demo.dao.inMemoryGame;

import com.example.demo.dto.GamesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface GameDaoJpa extends JpaRepository<GamesEntity, UUID> {
}
