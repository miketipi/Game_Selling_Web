package com.example.server.Controller;

import com.example.server.Models.Game;
import com.example.server.Models.User;
import com.example.server.Service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/game")
public class GameController {
    @Autowired
    private GameService gameService;
    @GetMapping(value = "/all")
    @ResponseBody
    List<Game> getAll(){
        return gameService.getAllGame();
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    Optional <Game> getGameById( @PathVariable("id") Long id){
        return gameService.getGameById(id);
    }


}
