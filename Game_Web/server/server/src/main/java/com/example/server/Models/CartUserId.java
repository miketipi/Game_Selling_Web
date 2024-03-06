package com.example.server.Models;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Builder
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class CartUserId  implements Serializable {
    private Long cartId;
    private Long userId;




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartUserId that = (CartUserId) o;
        return Objects.equals(cartId, that.cartId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, userId);
    }
}
