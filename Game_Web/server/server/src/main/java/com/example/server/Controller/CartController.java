package com.example.server.Controller;

import com.example.server.DTO.AddCartItemDTO;
import com.example.server.Models.CartItem;
import com.example.server.Service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/cart")
public class CartController {
public static final Logger logger = Logger.getLogger("CartItem");
    @Autowired
    private CartItemService cartItemService;
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    @ResponseBody
    List<CartItem> getAllCartItem(){
        return cartItemService.getAll();
    }

    @PostMapping("/add")
    void addCartItem(@RequestBody AddCartItemDTO addCartItemDTO){
        logger.info("Dang them vao cart cua cartId " + addCartItemDTO.getCartId() + " San pham co ID la: " + addCartItemDTO.getProductId());
        cartItemService.addCartItem(addCartItemDTO.getCartId(), addCartItemDTO.getProductId());
    }

    @PostMapping("/delete")
    void deleteCartItem(@RequestBody AddCartItemDTO addCartItemDTO){
        logger.info("Dang xoa khoi cart cua cartId: " + addCartItemDTO.getCartId() + " San pham co ID la: " + addCartItemDTO.getProductId());
        cartItemService.deleteCartItem(addCartItemDTO.getCartId(), addCartItemDTO.getProductId());
    }
}
