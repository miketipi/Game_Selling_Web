package com.example.server.Controller;

import com.example.server.DTO.AddCartItemDTO;
import com.example.server.Models.CartItem;
import com.example.server.Service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartItemService cartItemService;
    @GetMapping("/all")
    @ResponseBody
    List<CartItem> getAllCartItem(){
        return cartItemService.getAll();
    }

//    @PostMapping("/add")
//    void addCartItem(@RequestBody AddCartItemDTO addCartItemDTO){
//        cartItemService.addCartItem(addCartItemDTO.getCartId(), addCartItemDTO.getProductId());
//    }
}
