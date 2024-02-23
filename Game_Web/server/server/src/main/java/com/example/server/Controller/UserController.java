package com.example.server.Controller;

import com.example.server.Models.User;
import com.example.server.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping(value = "/get")
    @ResponseBody
    List<User> getAllUsers(){
        return userService.getAllUser();
    }
}
