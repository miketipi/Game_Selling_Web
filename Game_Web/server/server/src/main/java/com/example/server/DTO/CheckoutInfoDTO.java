package com.example.server.DTO;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutInfoDTO {
    private Long total;
    private String orderInfo;
    private String returnUrl;
}
