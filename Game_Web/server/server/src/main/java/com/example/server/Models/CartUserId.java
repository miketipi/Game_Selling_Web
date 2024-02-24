package com.example.server.Models;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Builder
@NoArgsConstructor
@Getter
@Setter
public class CartUserId  implements Serializable {
    private Long cartId;
    private Long userId;

    public CartUserId(Long cartId, Long userId){
        this.cartId = cartId;
        this.userId = userId;
    }


}
