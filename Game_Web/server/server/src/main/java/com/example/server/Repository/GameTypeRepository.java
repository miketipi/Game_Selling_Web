package com.example.server.Repository;

import com.example.server.Models.Game;
import com.example.server.Models.GameType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface GameTypeRepository extends JpaRepository<GameType, Long> {
    @Query(value = "select * from gametype", nativeQuery = true)
    List<GameType> getAllGameType();

    @Query(value = "insert into gametype (game_type_name) values(:game_type_name)", nativeQuery = true)
    void addGameType (@Param("game_type_name") String game_type_name);

//    @Query(value = "select *  \n" + "from game \n" + "where game.game_type_id = :id", nativeQuery = true)
//    Optional<List<Game>> getGamesByGameType(@Param("id") Long id);
    @Query(value = "SELECT game_type_name from game_type where game_type.id = :id", nativeQuery = true)
    String getGameTypeName(@Param("id") Long id);

    @Modifying
    @Query(value = "update gametype \n" + "set game_type_name = :name \n" + " where gametype.id = :id", nativeQuery = true)
    void updateGameType(@Param("id") Long id, @Param("name") String  name);

    @Modifying
    @Query(value = "update gametype \n" + "set deleted = true \n" + "where gametype.id = id", nativeQuery = true)
    void deleteGameType(@Param("id") Long id);


}
