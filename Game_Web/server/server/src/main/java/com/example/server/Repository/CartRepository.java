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
@Query(value = "select cart_id from cart where user_id = :userId", nativeQuery = true)
    Long getCartIdByUserId(@Param("userId") Long userId);

@Query(value = "select * from cart where user_id = :userId", nativeQuery = true)
    Cart getCartByUserId(@Param("userId") Long userId);
}
