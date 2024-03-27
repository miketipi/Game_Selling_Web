package com.example.server.Repository;

import com.example.server.Models.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
    @Query(value = "select fav_id from favourite where user_id = :userId", nativeQuery = true)
    Long getFavIdByProductId(@Param("userId") Long userId);
}
