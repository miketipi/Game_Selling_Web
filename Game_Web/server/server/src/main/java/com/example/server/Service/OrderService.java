package com.example.server.Service;

import com.example.server.DTO.CreateOrderDTO;
import com.example.server.Models.Orders;
import com.example.server.Repository.OrderRepository;
import com.example.server.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


public interface OrderService {
    Orders createOrders(CreateOrderDTO createOrderDTO);

    List<Orders> getAllOrders();

    List <Orders> getAllOrdersByUser(Long userId);
    void createOrdersByUserId(Long userId, Long cartId, BigDecimal totalMoney,Long totalQuantity);

    Boolean updateOrderStatus(Long id);

}
