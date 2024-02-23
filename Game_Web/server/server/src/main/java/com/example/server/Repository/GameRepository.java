package com.example.server.Repository;

import com.example.server.Models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository  extends JpaRepository <Game,Long> {
    @Query(value = "SELECT platform_name from platform where platform.id = :id", nativeQuery = true)
    String getPlatformName(@Param("id") Long id);
//    @Override
//    Optional<Game> findById(Long id);
}
