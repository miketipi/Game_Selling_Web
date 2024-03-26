package com.example.server.Controller;

import com.example.server.DTO.AddCartItemDTO;
import com.example.server.Models.CartItem;
import com.example.server.Repository.CartRepository;
import com.example.server.Repository.UserRepository;
import com.example.server.Service.CartItemService;
import com.example.server.Service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/cart")
public class CartController {
public static final Logger logger = Logger.getLogger("CartItem");
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    @ResponseBody
    List<CartItem> getAllCartItem(){
        return cartItemService.getAll();
    }


    @GetMapping("/{id}")
    List<CartItem> getAllCartItemById(@PathVariable("id") Long id){
        return cartItemService.getAllCartItemById(id);
    }
    @PostMapping("/add")
    void addCartItem(@RequestHeader("Authorization") String header,@RequestBody AddCartItemDTO addCartItemDTO){
        String jwt = header.substring(7);
        String username = jwtService.extractUserName(jwt);
        Long id = userRepository.findByName(username).get().getUserId();
        Long cartId = cartRepository.getCartIdByUserId(id);
        logger.info("Dang them vao cart cua cartId " + cartId + " San pham co ID la: " + addCartItemDTO.getProductId());
        cartItemService.addCartItem(cartId, addCartItemDTO.getProductId());
    }

    @PostMapping("/delete")
    void deleteCartItem(@RequestHeader("Authorization") String header , @RequestBody AddCartItemDTO addCartItemDTO){
        String jwt = header.substring(7);
        String username = jwtService.extractUserName(jwt);
        Long id = userRepository.findByName(username).get().getUserId();
        Long cartId = cartRepository.getCartIdByUserId(id);
        logger.info("Dang xoa khoi cart cua cartId: " + cartId + " San pham co ID la: " + addCartItemDTO.getProductId());
        cartItemService.deleteCartItem(cartId, addCartItemDTO.getProductId());
    }
}
