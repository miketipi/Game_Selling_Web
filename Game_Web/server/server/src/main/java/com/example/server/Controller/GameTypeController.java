package com.example.server.Controller;

import com.example.server.Models.Game;
import com.example.server.Models.GameType;
import com.example.server.Repository.GameTypeRepository;
import com.example.server.Service.GameTypeService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "/gametype")
public class GameTypeController {
    public static final Logger logger = Logger.getLogger("GameType");
    @Autowired
    private GameTypeService gameTypeService;

    @GetMapping(value = "/all")
    @ResponseBody
    List<GameType> getAllGameType(){
        logger.info("Lay Tat Ca Loai Game");
        return  gameTypeService.getAllGameType();
    }

//    @GetMapping(value = "/gameoftype/{id}")
//    @ResponseBody
//    Optional<List<Game>> getGamesByType(@PathVariable("id") Long id){
//        return  gameTypeService.getAllGameByType(id);
//    }

}
