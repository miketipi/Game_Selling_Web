package com.example.server.Repository;

import com.example.server.Models.FavouriteItem;
import com.example.server.Models.FavouriteProductId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoriteItemRepository extends JpaRepository<FavouriteItem, FavouriteProductId> {
    @Modifying
    @Query(value = "insert into favourite_item(fav_id, product_id) values (:favId, :productId)", nativeQuery = true)
    @Transactional
    void addFavoriteItem(@Param("favId") Long favId, @Param("productId") Long productId);
    @Modifying
    @Query(value = "update favourite_item set deleted = 1 \n" + "where fav_id = :favId \n" + "and product_id = :productId", nativeQuery = true)
    @Transactional
    void softDelete(@Param("favId") Long favId, @Param("productId") Long productId);

    @Query(value = "select * from favourite_item where fav_id = :favId", nativeQuery = true)
    List<FavouriteItem> getAllFavouriteItemById(@Param("favId") Long favId);
}
