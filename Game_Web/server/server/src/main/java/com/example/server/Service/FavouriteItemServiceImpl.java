package com.example.server.Service;

import com.example.server.Models.CartItem;
import com.example.server.Models.FavouriteItem;
import com.example.server.Repository.FavoriteItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavouriteItemServiceImpl implements FavouriteItemService {
    @Autowired
    private FavoriteItemRepository favoriteItemRepository;

    @Override
    public List<FavouriteItem> getAll() {
        List<FavouriteItem> getAllFromDB = new ArrayList<>();
        List<FavouriteItem> allFav = favoriteItemRepository.findAll();
        for (FavouriteItem favItem : allFav) {
            if (favItem.getDeleted() == false) {
                getAllFromDB.add(favItem);
            }
        }
        return getAllFromDB;
    }

    @Override
    public void addFavItem(Long favId, Long productId) {
        favoriteItemRepository.save(new FavouriteItem(productId, favId));
    }

    @Override
    public void softDelete(Long favId, Long productId) {
        favoriteItemRepository.softDelete(favId, productId);
    }

    @Override
    public List<FavouriteItem> getAllFavouriteItemById(Long favId) {
        return favoriteItemRepository.getAllFavouriteItemById(favId);
    }
}
