package com.example.server.Controller;

import com.example.server.DTO.ModifyUserDTO;
import com.example.server.DTO.OtherUserDTO;
import com.example.server.DTO.UpdatePasswordDTO;
import com.example.server.Models.Comments;
import com.example.server.Models.CustomUserDetails;
import com.example.server.Models.User;
import com.example.server.Service.UserService;
import io.jsonwebtoken.MalformedJwtException;
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
    List<User> getAllUsers() {
        logger.info("Get all Users");
        try {
            return userService.getAllUser();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    OtherUserDTO getUserById(@PathVariable("id") Long id) {
        logger.info("Lay thong tin nguoi dung co id" + id);
        Optional<User> a = userService.getUserById(id);
        return new OtherUserDTO(a.get().getUser_name(), a.get().getAddress(), a.get().getPhone());
    }


//    @PostMapping(value = "/me")
//    @ResponseBody
//    ResponseEntity<Boolean> modifyUser(@RequestHeader("Authorization") String header, @RequestBody ModifyUserDTO modifyUserDTO) {
//        String jwt = header.substring(7);
//        logger.info("Dang thay doi thong tin nguoi dung co id : " + modifyUserDTO.getUserId());
//        Boolean result = userService.updateUser(modifyUserDTO, jwt);
//        if (result == true) return ResponseEntity.ok(result);
//        else return ResponseEntity.badRequest().body(false);
//
//    }

    @PostMapping(value = "/me")
    @ResponseBody
    ResponseEntity<Boolean> modifyUser(@RequestBody ModifyUserDTO modifyUserDTO) {
        logger.info("Dang thay doi thong tin nguoi dung co id : " + modifyUserDTO.getUserId());
        Boolean result = userService.updateUser(modifyUserDTO);
        if (result == true) return ResponseEntity.ok(result);
        else return ResponseEntity.badRequest().body(false);

    }

    @PostMapping("/me/password")
    @ResponseBody
    ResponseEntity<Boolean> changePassword(@RequestHeader("Authorization") String header, UpdatePasswordDTO updatePasswordDTO){
        String jwt = header.substring(7);
        logger.info("Dang thay doi mat khau nguoi dung co id: " + updatePasswordDTO);
        Boolean result = userService.updatePassword(updatePasswordDTO,jwt);
        if(result == true) return  ResponseEntity.ok(result);
        else return  ResponseEntity.badRequest().body(false);

    }

    @GetMapping(value = "/me")
    @ResponseBody
    Optional<User> getMyInformation(@RequestHeader("Authorization") String header) throws Exception {
        logger.info("Lay thong tin nguoi dung");
        logger.info("Noi dung header Authorization : " + header);
        try {
            String jwtToken = header.substring(7); //bat dau cat jwt sau chuoi bearer
            Optional<User> a = userService.getMyInformation(jwtToken);
            return a;
        } catch (MalformedJwtException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping(value = "/me/comments")
    @ResponseBody
    List<Comments> getAllCommentsByUser(@RequestHeader("Authorization") String header) throws Exception {
        logger.info("Lay moi comments lien quan den nguoi dung");
        try {
            String jwt = header.substring(7);
            List<Comments> userComments = userService.getAllCommentsByUser(jwt);
            return userComments;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
