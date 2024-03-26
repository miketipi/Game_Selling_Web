package com.example.server.DAO;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NewCartItemRepository {
    @Query(value = "update cart_item set deleted = 1 \n" + "where cart_id = :cartId \n" + "and product_id = :productId", nativeQuery = true)
    @Transactional
    void softDelete(@Param("cartId") Long cartId, @Param("productId") Long productId);
}
