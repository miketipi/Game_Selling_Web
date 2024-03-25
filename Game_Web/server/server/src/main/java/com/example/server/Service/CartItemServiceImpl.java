package com.example.server.Service;

import com.example.server.Models.CartItem;
import com.example.server.Repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl  implements CartItemService{
    @Autowired
    private CartItemRepository cartItemService;


    @Override
    public void addCartItem(Long userId, Long productId) {
        cartItemService.addCartItem(userId, productId);
    }

    @Override
    public List<CartItem> getAll(){
        return cartItemService.findAll();
    }
}
