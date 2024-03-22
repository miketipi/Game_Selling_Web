package com.example.server.Service;

import com.example.server.Models.Game;
import com.example.server.Models.GameType;

import java.util.List;
import java.util.Optional;

public interface GameTypeService {
    List<GameType> getAllGameType();
//    Optional<List<Game>> getAllGameByType(Long id);
    void updateGameType(Long id, String name);

    void deleteGameType(Long id);
}
