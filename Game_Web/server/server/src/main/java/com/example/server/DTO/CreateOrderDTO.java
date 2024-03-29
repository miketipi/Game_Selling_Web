package com.example.server.DTO;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderDTO {
    private Long userId;
    private Long cartId;
    private BigDecimal total;
    private Long quantity;
}
