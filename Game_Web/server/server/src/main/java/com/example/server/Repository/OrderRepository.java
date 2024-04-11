package com.example.server.Repository;

import com.example.server.Models.Orders;
import jakarta.transaction.Transactional;
import org.hibernate.query.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    @Query(value = "select * from orders where user_id = :userId", nativeQuery = true)
    List<Orders> getAllOrdersByUser(@Param("userId") Long userId);

    default Orders createOrder(Long userID, Long cartId, BigDecimal totalPrice, Long totalQuantity){
        Orders a = Orders.builder().userId(userID).cartId(cartId).total(totalPrice).quantity(totalQuantity).orderStatus(false).build();
        Orders b = this.save(a);
        return b;
    }

    @Modifying
    @Transactional
    @Query(value = "update orders set status = true where order_id = :id", nativeQuery = true)
    void updateByCartIdOrderByDeleted(@Param("id") Long id);
}
