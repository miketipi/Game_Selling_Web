package com.example.server.Service;

import com.example.server.Models.GameType;
import com.example.server.Repository.GameTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameTypeServiceImpl implements GameTypeService {
    @Autowired
    private GameTypeRepository gameTypeRepository;
    @Override
    public List<GameType> getAllGameType(){
        return gameTypeRepository.getAllGameType();
    }
}
