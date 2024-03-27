package com.example.server.Repository;

import com.example.server.Models.Orders;
import org.hibernate.query.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderRepository, Long> {
    @Query(value = "select * from orders where user_id = :userId", nativeQuery = true)
    List<Orders> getAllOrdersByUser(@Param("userId") Long userId);
}
