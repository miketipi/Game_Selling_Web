package com.example.server.Service;

import com.example.server.Models.CartItem;
import com.example.server.Models.CartUserId;

import java.util.List;

public interface CartItemService {
    void addCartItem(Long userId, Long productId);
    List<CartItem> getAll();
    void addCartItemById(CartUserId cartUserId);

    void deleteCartItem(Long userId, Long productId);
}
