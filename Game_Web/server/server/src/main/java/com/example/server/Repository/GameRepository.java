package com.example.server.Repository;

import com.example.server.Models.Game;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public interface GameRepository  extends JpaRepository <Game,Long> {
//    @Query(value = "SELECT platform_name from platform where platform.id = :id", nativeQuery = true)
//    String getPlatformName(@Param("id") Long id);
//    @Override
//    Optional<Game> findById(Long id);
//    @Query(value = "SELECT publisher_name from publisher where publisher.id = :id", nativeQuery = true)
//    String getPublisherName(@Param("id") Long id);
    @Query(value = "SELECT game_type_name from game_type where game_type.id = :id", nativeQuery = true)
    String getGameTypeName(@Param("id") Long id);

    @Query(value = "select *  \n" + "from game \n" + "where game.game_type_id = :id", nativeQuery = true)
    List<Game> getGamesGameType(@Param("id") Long id);

    @Query(value = "select  * \n" + "from game \n" + "where game.publisher_id = :publisher_id", nativeQuery = true)
    List<Game> getGamesByPublisher(@Param("publisher_id") Long publisher_id);

    @Query(value = "select  * \n" + "from game \n" + "where game.platform_id = :id", nativeQuery = true)
    List<Game> getGameByPlatform(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "insert into game (game_type_id, game_name, game_price, game_image, game_rating, game_status, platform_id, game_version, game_downloaded, publisher_id) values (:gameTypeId, :gameName, :gamePrice, :gameImage, :gameRating, :gameStatus, :platformId, :gameVersion, :gameDownloaded, :publisherId)", nativeQuery = true)
    void insertGame(@Param("gameTypeId") Long gameTypeId, @Param("gameName") String gameName, @Param("gamePrice") BigDecimal gamePrice, @Param("gameImage") String gameImage, @Param("gameRating") Float gameRating, @Param("gameStatus") String gameStatus, @Param("platformId") Long platformId,@Param("gameVersion") String gameVersion, @Param("gameDownloaded") Integer gameDownloaded, @Param("publisherId") Long publisherId );

    @Modifying
    @Transactional
    @Query(value = "update game \n" + "set game_type_id = :gameTypeId, game_name = :gameName, game_price = :gamePrice, game_image = :gameImage, game_rating = :gameRating, game_status = :gameStatus, platform_id = :platformId, game_version = :gameVersion, game_downloaded = :gameDownloaded, publisher_id = :publisherId \n" + "where product_id = :productId", nativeQuery = true)
    void updateGameById(@Param("gameTypeId") Long gameTypeId, @Param("gameName") String gameName, @Param("gamePrice") BigDecimal gamePrice, @Param("gameImage") String gameImage, @Param("gameRating") Float gameRating, @Param("gameStatus") String gameStatus, @Param("platformId") Long platformId, @Param("gameVersion") String gameVersion, @Param("gameDownloaded") Integer gameDownloaded, @Param("publisherId") Long publisherId, @Param("productId") Long productId );


    @Modifying
    @Transactional
    @Query(value = "update game \n" + "set deleted = 1 \n" + "where game.id = :id", nativeQuery = true)
    void deleteGameById(@Param("id") Long id);
//    @Modifying
//    @Transactional
//    @Query(value = "update game \n" + "set ", nativeQuery = true)

}
