package com.example.server.Service;

import com.example.server.DTO.OldCartsDTO;
import com.example.server.Models.Cart;
import com.example.server.Models.User;
import com.example.server.Repository.CartItemRepository;
import com.example.server.Repository.CartRepository;
import com.example.server.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Override
    public List<OldCartsDTO> getHistory(String userName) {
        User a = userRepository.findByName(userName).get();
        List<Cart> cartHistory = cartRepository.getUserCartHistory(a.getUserId());
        List<OldCartsDTO> oldListCart = new ArrayList<>();
        for(Cart cart : cartHistory){
            oldListCart.add(OldCartsDTO.builder().cartId(cart.getCartId()).userId(cart.getUserId()).allItems(cartItemRepository.getAllCartItemByCartId(cart.getCartId())).totalPrice(cart.getTotalMoney()).quantity(cart.getTotalQuantity()).build());
        }
        return oldListCart;
    }
//    @Autowired
//    private Cart
}
