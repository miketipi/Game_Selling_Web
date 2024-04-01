package com.example.server.DTO;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CartResponseDTO {
    private BigDecimal totalMoney;
    private Long totalQuantity;
}
