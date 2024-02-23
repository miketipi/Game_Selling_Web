package com.example.server.Repository;

import com.example.server.Models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository  extends JpaRepository <Game,Long> {
//    @Override
//    Optional<Game> findById(Long id);
}
