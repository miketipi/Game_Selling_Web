package com.example.server.Models;

import java.io.Serializable;
import java.util.Objects;

public class FavouriteProductId implements Serializable {
    private Long favId;
    private Long productId;




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavouriteProductId that = (FavouriteProductId) o;
        return Objects.equals(favId, that.favId) && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(favId, productId);
    }
}
