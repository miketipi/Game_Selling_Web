package com.example.server.Controller;

import com.example.server.DTO.AddCartItemDTO;
import com.example.server.DTO.AllCartInformationDTO;
import com.example.server.DTO.CartResponseDTO;
import com.example.server.DTO.CreateOrderDTO;
import com.example.server.Models.Cart;
import com.example.server.Models.CartItem;
import com.example.server.Repository.CartRepository;
import com.example.server.Repository.UserRepository;
import com.example.server.Service.CartItemService;
import com.example.server.Service.JwtService;
import com.example.server.Service.OrderService;
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

    @Autowired
    private OrderService orderService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    @ResponseBody
    List<CartItem> getAllCartItem() {
        return cartItemService.getAll();
    }


    //    @GetMapping("/me")
//    List<CartItem> getAllCartItemById(@RequestHeader("Authorization") String header){
//        String jwt = header.substring(7);
//        String username = jwtService.extractUserName(jwt);
//        Long id = userRepository.findByName(username).get().getUserId();
//        Long cartId = cartRepository.getCartIdByUserId(id);
//        return cartItemService.getAllCartItemById(cartId);
//    }
    @GetMapping("/me")
    AllCartInformationDTO getAllCartItemById(@RequestHeader("Authorization") String header) {

        String jwt = header.substring(7);
        String username = jwtService.extractUserName(jwt);
        Long id = userRepository.findByName(username).get().getUserId();
        logger.info("Dang lay het thong tin cart cua nguoi dung id" + id);
        Long cartId = cartRepository.getCartIdByUserId(id);
        CartResponseDTO cartResponseDTO = new CartResponseDTO(cartRepository.findById(cartId).get().getTotalMoney(), cartRepository.findById(cartId).get().getTotalQuantity());
        return new AllCartInformationDTO(cartItemService.getAllCartItemById(cartId), cartResponseDTO);
    }

    @PostMapping("/add")
    void addCartItem(@RequestHeader("Authorization") String header, @RequestBody AddCartItemDTO addCartItemDTO) {
        String jwt = header.substring(7);
        String username = jwtService.extractUserName(jwt);
        Long id = userRepository.findByName(username).get().getUserId();
        Long cartId = cartRepository.getCartIdByUserId(id);
        logger.info("Dang them vao cart cua cartId " + cartId + " San pham co ID la: " + addCartItemDTO.getProductId());
        cartItemService.addCartItem(cartId, addCartItemDTO.getProductId());
    }

    @PostMapping("/order")
    void createOrder(@RequestBody CreateOrderDTO createOrderDTO){
        orderService.createOrders(createOrderDTO);
    }
    @PostMapping("/delete")
    void deleteCartItem(@RequestHeader("Authorization") String header, @RequestBody AddCartItemDTO addCartItemDTO) {
        String jwt = header.substring(7);
        String username = jwtService.extractUserName(jwt);
        Long id = userRepository.findByName(username).get().getUserId();
        Long cartId = cartRepository.getCartIdByUserId(id);
        logger.info("Dang xoa khoi cart cua cartId: " + cartId + " San pham co ID la: " + addCartItemDTO.getProductId());
        cartItemService.deleteCartItem(cartId, addCartItemDTO.getProductId());
    }
}
