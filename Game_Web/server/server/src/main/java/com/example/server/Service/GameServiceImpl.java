package com.example.server.Service;

import com.example.server.Models.Game;
import com.example.server.Repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameRepository gameRepository;
    @Override
    public List<Game> getAllGame(){
        return gameRepository.findAll();
    }
    @Override
    public Optional<Game> getGameById(Long id){
        return gameRepository.findById(id);
    }
    @Override
    public List<Game> getAllGameByGameType(Long id){
        return gameRepository.getGamesGameType(id);
    }
    @Override
    public List<Game> getGameByPlatform(Long id){
        return gameRepository.getGameByPlatform(id);
    }
    @Override
    public List<Game> getGameByPublisher(Long id){
        return  gameRepository.getGamesByPublisher(id);
    }
}
