package com.example.server.Controller;

import com.example.server.Models.Game;
import com.example.server.Models.GameType;
import com.example.server.Repository.GameTypeRepository;
import com.example.server.Service.GameTypeService;
import jakarta.transaction.Transactional;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/delete")
    void deleteGameType(@RequestBody Long id){
        logger.info("Xoa game type co ma id : " + id);
        gameTypeService.deleteGameType(id);
    }


    @Transactional
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/{id}")
    void updateGametype(@PathVariable("id") Long id, String name){
logger.info("Update thong tin loai game voi id : " + id);
gameTypeService.updateGameType(id,name);
    }

//    @GetMapping(value = "/gameoftype/{id}")
//    @ResponseBody
//    Optional<List<Game>> getGamesByType(@PathVariable("id") Long id){
//        return  gameTypeService.getAllGameByType(id);
//    }

}
