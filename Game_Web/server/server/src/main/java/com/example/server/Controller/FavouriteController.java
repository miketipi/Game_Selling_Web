package com.example.server.Controller;

import com.example.server.Models.FavouriteItem;
import com.example.server.Service.FavouriteItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fav")
public class FavouriteController {
    @Autowired
    private FavouriteItemService favouriteItemService;
}
