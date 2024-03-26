package com.example.server.Controller;

import com.example.server.Models.FavouriteItem;
import com.example.server.Service.FavouriteItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/fav")
public class FavouriteController {
    @Autowired
    private FavouriteItemService favouriteItemService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseBody
    List<FavouriteItem> getAllFavItem(){
        return favouriteItemService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    List<FavouriteItem> getAllFavItemById(@PathVariable("id") Long id){
        return favouriteItemService.getAllFavouriteItemById(id);
    }


}
