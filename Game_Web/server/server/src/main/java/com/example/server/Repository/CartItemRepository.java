package com.example.server.Repository;

import com.example.server.Models.CartItem;
import com.example.server.Models.CartUserId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, CartUserId> {
    @Query(value = "insert into cart_item(cart_id, product_id) values (:cartId, :productId)", nativeQuery = true)
    @Transactional
    void addCartItem(@Param("cartId") Long cartId, @Param("productId") Long productId);
    @Modifying
    @Query(value = "update cart_item set deleted = 1 \n" + "where cart_id = :cartId \n" + "and product_id = :productId", nativeQuery = true)
    @Transactional
    void softDelete(@Param("cartId") Long cartId, @Param("productId") Long productId);

    @Query(value = "select * from cart_item where cart_id = :cartId", nativeQuery = true)
    List<CartItem> getAllCartItemByCartId(@Param("cartId") Long cartId);

}
