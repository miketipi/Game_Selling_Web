package com.example.server.Service;

import com.example.server.Models.Game;
import com.example.server.Repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public interface GameService {
    List<Game> getAllGame();
    Optional<Game> getGameById(Long id);

    List<Game> getAllGameByGameType(Long id);
    List<Game> getGameByPlatform(Long id);
    List<Game> getGameByPublisher(Long id);

    void insertGame(Long gameTypeId, String gameName, Long gamePrice, String gameImage, Float gameRating, String gameStatus, Long platformId, String gameVersion, Integer gameDownloaded, Long publisherId);

    void updateGame(Long gameTypeId, String gameName, Long gamePrice, String gameImage, Float gameRating, String gameStatus, Long platformId, String gameVersion, Integer gameDownloaded, Long publisherId, Long id);
}
