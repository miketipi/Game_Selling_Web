package com.example.server.Service;

import com.example.server.DTO.CreateOrderDTO;
import com.example.server.Models.Orders;
import com.example.server.Repository.OrderRepository;
import com.example.server.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartService cartService;
    public Orders createOrders(CreateOrderDTO createOrderDTO){
        Orders a = orderRepository.createOrder(createOrderDTO.getUserId(),createOrderDTO.getCartId(),createOrderDTO.getTotal(),createOrderDTO.getQuantity());
        return a;
    }
}
