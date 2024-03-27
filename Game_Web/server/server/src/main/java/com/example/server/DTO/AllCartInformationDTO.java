package com.example.server.DTO;

import com.example.server.Models.Cart;
import com.example.server.Models.CartItem;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AllCartInformationDTO {
    private List<CartItem> allCartItem;
    private CartResponseDTO cartInfo;

}
