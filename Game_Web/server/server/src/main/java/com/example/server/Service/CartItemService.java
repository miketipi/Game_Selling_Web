package com.example.server.Service;

import com.example.server.Models.CartItem;

import java.util.List;

public interface CartItemService {
    void addCartItem(Long userId, Long productId);
    List<CartItem> getAll();
}
