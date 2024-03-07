package com.example.server.Controller;

import com.example.server.Models.User;
import com.example.server.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    public static Logger logger = Logger.getLogger("User");
    @Autowired
    private UserService userService;
    @GetMapping(value = "/all")
    @ResponseBody
    List<User> getAllUsers(){
        logger.info("Get all Users");
        return userService.getAllUser();
    }
}
