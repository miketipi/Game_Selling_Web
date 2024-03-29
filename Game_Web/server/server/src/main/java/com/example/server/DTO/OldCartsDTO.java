package com.example.server.DTO;

import com.example.server.Models.CartItem;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OldCartsDTO {
    private Long cartId;
    private Long userId;
    private List<CartItem> allItems;
    private  Long quantity;
    private BigDecimal totalPrice;
}
