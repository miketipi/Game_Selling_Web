package com.example.server.Controller;

import com.example.server.DTO.AddFavouriteItemDTO;
import com.example.server.Models.FavouriteItem;
import com.example.server.Repository.UserRepository;
import com.example.server.Service.FavouriteItemService;
import com.example.server.Service.FavouriteService;
import com.example.server.Service.JwtService;
import com.example.server.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/fav")
public class FavouriteController {
    @Autowired
    private FavouriteItemService favouriteItemService;
@Autowired
private FavouriteService favouriteService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;

    private static final Logger logger = Logger.getLogger("Favourite");
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

    @GetMapping("/me")
    @ResponseBody
    List<FavouriteItem> getAllFavItemById(@RequestHeader("Authorization") String header){
        String jwt = header.substring(7);
        String username = jwtService.extractUserName(jwt);
        Long userId = userService.findUserByName(username).get().getUserId();
        Long favId = favouriteService.getFavIdByUserId(userId);
        logger.info("Lay Favourite voi userId la : " + userId + " va favId la : " + favId);
        return favouriteItemService.getAllFavouriteItemById(favId);

    }

    @PostMapping("/add")
    void addFavouriteItem (@RequestHeader("Authorization") String header, AddFavouriteItemDTO addFavouriteItemDTO){
        String jwt = header.substring(7);
        String username = jwtService.extractUserName(jwt);
        Long userId = userService.findUserByName(username).get().getUserId();
        Long favId = favouriteService.getFavIdByUserId(userId);
        logger.info("Dang them san pham co Id la : " +  addFavouriteItemDTO.getProductId()+ " vao favourite :" + favId);
        favouriteItemService.addFavItem(favId, addFavouriteItemDTO.getProductId());
    }
    @PostMapping("/delete")
    void softDeleteFavouriteItem(@RequestHeader("Authorization") String header, AddFavouriteItemDTO addFavouriteItemDTO){
        String jwt = header.substring(7);
        String username = jwtService.extractUserName(jwt);
        Long userId = userService.findUserByName(username).get().getUserId();
        Long favId = favouriteService.getFavIdByUserId(userId);
        favouriteItemService.softDelete(favId,addFavouriteItemDTO.getProductId());
    }



}
