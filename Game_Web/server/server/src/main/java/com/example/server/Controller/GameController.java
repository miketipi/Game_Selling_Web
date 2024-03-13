package com.example.server.Controller;

import com.example.server.DTO.InsertGameDTO;
import com.example.server.DTO.ModifyGameDTO;
import com.example.server.Models.Game;
import com.example.server.Service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "/game")
public class GameController {
    public static final Logger logger = Logger.getLogger("Game");
    @Autowired
    private GameService gameService;
    @GetMapping(value = "/all")
    @ResponseBody
    List<Game> getAll(){
        logger.info("Lay Tat Ca Game");
        return gameService.getAllGame();
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    Optional <Game> getGameById( @PathVariable("id") Long id){
        logger.info("Truy cap game theo id : " + id);
        return gameService.getGameById(id);
    }

    @GetMapping(value = "/bygametype/{id}")
    @ResponseBody
    List<Game> getGameByGameType(@PathVariable("id") Long id){
        logger.info("Lay cac game theo the loai " + id);
        return gameService.getAllGameByGameType(id);
    }
    @GetMapping(value = "/byplatform/{id}")
    @ResponseBody
    List<Game> getGameByPlatform(@PathVariable("id") Long id){
        logger.info("Lay cac game theo nen tang " + id);
        return gameService.getGameByPlatform(id);
    }

    @GetMapping(value = "/bypublisher/{id}")
    @ResponseBody
    List<Game> getGameByPublisher(@PathVariable("id") Long id){
        logger.info("Lay cac game theo nha phat trien " + id);
        return gameService.getGameByPublisher(id);
    }

    @PostMapping(value = "/insert")
    void insertGame(@RequestBody InsertGameDTO insertGameDTO){
        logger.info("Dang them vao co so du lieu doi tuong game moi ");
        gameService.insertGame(insertGameDTO.getGameTypeId(), insertGameDTO.getGameName(), insertGameDTO.getGamePrice(),insertGameDTO.getGameImage(),insertGameDTO.getGameRating(),insertGameDTO.getGameStatus(),insertGameDTO.getPlatformId(),insertGameDTO.getGameVersion(),insertGameDTO.getGameDownloaded(),insertGameDTO.getPublisherId());
    };

    @PostMapping(value = "/update")
    void updateGame(@RequestBody ModifyGameDTO modifyGameDTO){
        logger.info("Dang update game voi gameId la : " + modifyGameDTO.getProductId());
        gameService.updateGame(modifyGameDTO.getGameTypeId(), modifyGameDTO.getGameName(), modifyGameDTO.getGamePrice(), modifyGameDTO.getGameImage(), modifyGameDTO.getGameRating(), modifyGameDTO.getGameStatus(), modifyGameDTO.getPlatformId(), modifyGameDTO.getGameVersion(), modifyGameDTO.getGameDownloaded(), modifyGameDTO.getPublisherId(), modifyGameDTO.getProductId());
    }
}
