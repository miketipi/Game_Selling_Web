package com.example.server.DTO;

import com.example.server.Models.CartUserId;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddCartItemDTO {
    private Long productId;
    private Long cartId;
}
