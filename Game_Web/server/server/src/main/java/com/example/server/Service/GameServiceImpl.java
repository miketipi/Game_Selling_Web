package com.example.server.Service;

import com.example.server.Models.Comments;
import com.example.server.Models.Game;
import com.example.server.Repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private CommentsService commentsService;

    @Override
    public List<Game> getAllGame() {
        List<Game> unDeletedGames = new ArrayList<Game>();
        List<Game> allGame = gameRepository.findAll();
        for (Game game : allGame) {
            if (!game.isDeleted()) {
                unDeletedGames.add(game);
            }
        }
        return unDeletedGames;
    }

    @Override
    public Optional<Game> getGameById(Long id) {
        return gameRepository.findById(id);
    }

    @Override
    public List<Game> getAllGameByGameType(Long id) {
        return gameRepository.getGamesGameType(id);
    }

    @Override
    public List<Game> getGameByPlatform(Long id) {
        return gameRepository.getGameByPlatform(id);
    }

    @Override
    public List<Game> getGameByPublisher(Long id) {
        return gameRepository.getGamesByPublisher(id);
    }

    @Override
    public void insertGame(Long gameTypeId, String gameName, BigDecimal gamePrice, String gameImage, Float gameRating, String gameStatus, Long platformId, String gameVersion, Integer gameDownloaded, Long publisherId) {
        gameRepository.insertGame(gameTypeId, gameName, gamePrice, gameImage, gameRating, gameStatus, platformId, gameVersion, gameDownloaded, publisherId);
    }

    @Override
    public void updateGame(Long gameTypeId, String gameName, BigDecimal gamePrice, String gameImage, Float gameRating, String gameStatus, Long platformId, String gameVersion, Integer gameDownloaded, Long publisherId, Long productId) {
        gameRepository.updateGameById(gameTypeId, gameName, gamePrice, gameImage, gameRating, gameStatus, platformId, gameVersion, gameDownloaded, publisherId, productId);
    }

    @Override
    public List<Comments> getAllCommentsByProductId(Long id) {
        return commentsService.getAllCommentsByProduct(id);
    }

    @Override
    public List<Game> getAllGameByPage(Integer page, Integer offset, String sortBy) {
        Pageable paging = PageRequest.of(page,offset, Sort.by(sortBy));
        List<Game> pagedResult =  gameRepository.findAll(paging).getContent();
        return pagedResult;
    }
}
