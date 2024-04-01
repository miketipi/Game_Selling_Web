package com.example.server.Service;

import com.example.server.DTO.CreateOrderDTO;
import com.example.server.Models.Orders;
import com.example.server.Repository.OrderRepository;
import com.example.server.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartService cartService;

    @Override
    public Orders createOrders(CreateOrderDTO createOrderDTO) {
        Orders a = orderRepository.createOrder(createOrderDTO.getUserId(), createOrderDTO.getCartId(), createOrderDTO.getTotal(), createOrderDTO.getQuantity());
        return a;
    }

    @Override
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Orders> getAllOrdersByUser(Long userId) {
        return orderRepository.getAllOrdersByUser(userId);
    }

    @Override
    public void createOrdersByUserId(Long userId, Long cartId, BigDecimal totalMoney, Long totalQuantity) {
        orderRepository.createOrder(userId, cartId, totalMoney, totalQuantity);
    }

    @Override
    public Boolean updateOrderStatus(Long id) {
        try {
            orderRepository.updateByCartIdOrderByDeleted(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
