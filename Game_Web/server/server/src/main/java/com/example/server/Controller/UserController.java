package com.example.server.Controller;

import com.example.server.DTO.*;
import com.example.server.Models.Comments;
import com.example.server.Models.CustomUserDetails;
import com.example.server.Models.User;
import com.example.server.Service.UserService;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    @Autowired
    private AuthenticationManager authenticationManager;
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
    ResponseEntity<SignUpResponseDTO> modifyUser(@RequestBody ModifyUserDTO modifyUserDTO) {
        logger.info("Dang thay doi thong tin nguoi dung co id : " + modifyUserDTO.getUserId() + modifyUserDTO.getUserName() +modifyUserDTO.getPhone() + modifyUserDTO.getAddress() + modifyUserDTO.getRealName());
        Boolean result = userService.updateUser(modifyUserDTO);
//        System.out.println(result);
        if (result == true){
            System.out.println("Ture");
            User afterModified = userService.findUserByName(modifyUserDTO.getUserName()).get();
//            System.out.println(afterModified.getUser_name());
//            System.out.println(afterModified.getPass_word());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(afterModified.getUser_name(),modifyUserDTO.getPassWord()));
            SignUpResponseDTO newUser = userService.login(LoginRequestDTO.builder().userName(afterModified.getUser_name()).passWord(modifyUserDTO.getPassWord()).build());
            return ResponseEntity.ok(newUser);
        }
        return ResponseEntity.badRequest().body(SignUpResponseDTO.builder().build());
        //Sau khi xong se cho logout va bat dang nhap lai

    }

    @PostMapping("/me/password")
    @ResponseBody
    ResponseEntity<Boolean> changePassword(UpdatePasswordDTO updatePasswordDTO){
        logger.info("Dang thay doi mat khau nguoi dung co id: " + updatePasswordDTO);
        Boolean result = userService.updatePassword(updatePasswordDTO);
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
