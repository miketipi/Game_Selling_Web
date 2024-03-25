package com.example.server.Repository;

import com.example.server.Models.CartItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Query(value = "insert into cart_item(cart_id, product_id) values (:cartId, :productId)", nativeQuery = true)
    @Transactional
    void addCartItem(@Param("cartId") Long cartId, @Param("productId") Long productId);
}
