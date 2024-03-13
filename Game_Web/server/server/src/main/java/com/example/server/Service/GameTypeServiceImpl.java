package com.example.server.Service;

import com.example.server.Models.Game;
import com.example.server.Models.GameType;
import com.example.server.Repository.GameTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class GameTypeServiceImpl implements GameTypeService {
    @Autowired
    private GameTypeRepository gameTypeRepository;
    @Override
    public List<GameType> getAllGameType(){
        return gameTypeRepository.getAllGameType();
    }
//    @Override
//    public Optional<List<Game>> getAllGameByType(Long id){
//        return gameTypeRepository.getGamesByGameType(id);
//    }
    @Override
    public void updateGameType(Long id, String name){
        gameTypeRepository.updateGameType(id, name);
    }
}
