package com.example.server.Controller;

import com.example.server.DTO.OtherUserDTO;
import com.example.server.Models.CustomUserDetails;
import com.example.server.Models.User;
import com.example.server.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    public static Logger logger = Logger.getLogger("User");
    @Autowired
    private UserService userService;

    @GetMapping(value = "/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseBody
    List<User> getAllUsers(){
        logger.info("Get all Users");
        try {
            return userService.getAllUser();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    OtherUserDTO getUserById(@PathVariable("id") Long id){
        logger.info("Lay thong tin nguoi dung co id" + id);
        Optional<User> a =  userService.getUserById(id);
        return new OtherUserDTO(a.get().getUser_name(), a.get().getAddress(), a.get().getPhone());
    }

    @GetMapping(value = "/me")
    @ResponseBody
    Optional<User> getMyInformation(@RequestHeader("Authorization") String header){
        logger.info("Lay thong tin nguoi dung");
        logger.info("Noi dung header Authorization : " + header);
        String jwtToken = header.substring(7); //bat dau cat jwt sau chuoi bearer
        Optional<User> a= userService.getMyInformation(jwtToken);
        return a;
    }
}
