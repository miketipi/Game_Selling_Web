package com.example.server.Service;

import com.example.server.Repository.FavouriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavouriteServiceImpl implements FavouriteService {
    @Autowired
    private FavouriteRepository favouriteRepository;

    @Override
    public Long getFavIdByUserId(Long id) {
        return favouriteRepository.getFavIdByProductId(id);
    }
}
