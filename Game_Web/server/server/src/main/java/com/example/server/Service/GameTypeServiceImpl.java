package com.example.server.Service;

import com.example.server.Models.Game;
import com.example.server.Models.GameType;
import com.example.server.Models.Platform;
import com.example.server.Repository.GameTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameTypeServiceImpl implements GameTypeService {
    @Autowired
    private GameTypeRepository gameTypeRepository;

    @Override
    public List<GameType> getAllGameType() {
        List<GameType> allPlatform = gameTypeRepository.getAllGameType();
        List<GameType> undeletedGameType = new ArrayList<GameType>();
        for (GameType platform : allPlatform) {
            if (!platform.getDeleted()) {
                undeletedGameType.add(platform);
            }
        }
        return undeletedGameType;
    }

    //    @Override
//    public Optional<List<Game>> getAllGameByType(Long id){
//        return gameTypeRepository.getGamesByGameType(id);
//    }
    @Override
    public void updateGameType(Long id, String name) {
        gameTypeRepository.updateGameType(id, name);
    }

    @Override
    public void deleteGameType(Long id) {
        gameTypeRepository.deleteGameType(id);
    }
}
