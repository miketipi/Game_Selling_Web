package com.example.server.Repository;

import com.example.server.Models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface GameRepository  extends JpaRepository <Game,Long> {
    @Query(value = "SELECT platform_name from platform where platform.id = :id", nativeQuery = true)
    String getPlatformName(@Param("id") Long id);
//    @Override
//    Optional<Game> findById(Long id);
    @Query(value = "SELECT publisher_name from publisher where publisher.id = :id", nativeQuery = true)
    String getPublisherName(@Param("id") Long id);
    @Query(value = "SELECT game_type_name from game_type where game_type.id = :id", nativeQuery = true)
    String getGameTypeName(@Param("id") Long id);
}
