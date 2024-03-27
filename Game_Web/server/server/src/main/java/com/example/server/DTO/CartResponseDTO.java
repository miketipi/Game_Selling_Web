package com.example.server.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CartResponseDTO {
    private Long totalMoney;
    private Long totalQuantity;
}
