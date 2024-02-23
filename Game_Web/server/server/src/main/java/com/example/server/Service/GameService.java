package com.example.server.Service;

import com.example.server.Models.Game;
import com.example.server.Repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public interface GameService {
    List<Game> getAllGame();
    Optional<Game> getGameById(Long id);
}
