package com.example.server.Service;

import com.example.server.Models.FavouriteItem;

import java.util.List;

public interface FavouriteItemService {
    List<FavouriteItem> getAll();
    void addFavItem(Long favId, Long productId);
    void softDelete(Long favId, Long productId);
}
