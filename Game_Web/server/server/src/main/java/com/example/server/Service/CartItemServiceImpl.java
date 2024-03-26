package com.example.server.Service;

import com.example.server.DAO.NewCartItemRepository;
import com.example.server.Models.Cart;
import com.example.server.Models.CartItem;
import com.example.server.Models.CartUserId;
import com.example.server.Repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartItemServiceImpl  implements CartItemService{
    @Autowired
    private CartItemRepository cartItemService;

//@Autowired
//private NewCartItemRepository newCartItemRepository;
    @Override
    public void addCartItem(Long cartId, Long productId) {
//        cartItemService.addCartItem(userId, productId);
        cartItemService.save(new CartItem(productId, cartId));
    }



    @Override
    public List<CartItem> getAll(){
        List<CartItem> checkingList = cartItemService.findAll();
        List<CartItem> realList = new ArrayList<>();
        for (CartItem cartItem : checkingList){
            if (cartItem.getDeleted() == false){
                realList.add(cartItem);
            }
        }
        return realList;
    }

    @Override
    public void addCartItemById(CartUserId cartUserId) {
    }

    @Override
    public void deleteCartItem(Long userId, Long productId) {
      //  cartItemService.delete(new CartItem(productId, userId));
        cartItemService.softDelete(userId, productId);
    }

    @Override
    public List<CartItem> getAllCartItemById(Long id) {
        return cartItemService.getAllCartItemByCartId(id);
    }
}
