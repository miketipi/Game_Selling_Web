package com.example.server.Repository;

import com.example.server.Models.Cart;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {


@Query(value = "select * from cart where user_id = :userId and delete = false", nativeQuery = true)
    Cart getCartByUserId(@Param("userId") Long userId);


@Query(value = "select cart_id from cart where user_id = :userId and deleted = false", nativeQuery = true)
Long getCartIdByUserId(@Param("userId") Long userId);

    @Query(value = "update cart set deleted = true where user_id = :userId", nativeQuery = true)
    void setSoftDelete(@Param("userId") Long userId);

    default void deleteCart(Long userId){
        setSoftDelete(userId);
        insertIntoCart(userId);
    }

    @Query(value = "insert into cart(user_id) values (:userId)", nativeQuery = true)
    void insertIntoCart(@Param("userId") Long userId);
    @Query(value = "select * from cart \n" + "where user_id = :id and deleted = true", nativeQuery = true)
    List<Cart> getUserCartHistory(@Param("id") Long id);
}



