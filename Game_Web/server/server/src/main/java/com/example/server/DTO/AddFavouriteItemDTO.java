package com.example.server.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddFavouriteItemDTO {
    private Long favId;
    private Long productId;
}
