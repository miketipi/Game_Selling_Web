package com.example.server.DTO;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderDTO {
    private Long id;
    private boolean orderStatus;
}
