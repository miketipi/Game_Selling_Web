package com.example.server.Repository;

import com.example.server.DTO.LoginRequestDTO;
import com.example.server.DTO.SignUpRequestDTO;
import com.example.server.Models.CustomUserDetails;
import com.example.server.Models.Role;
import com.example.server.Models.User;
import com.example.server.Service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User,Long>{
//    String getPlatformName(Long id);

    @Query(value = "select * from users where user_name = :name", nativeQuery = true)
    Optional<User> findByName(@Param("name") String name);

    @Modifying
    @Transactional
    @Query(value = "update users \n" + "set user_name = :userName, real_name = :realName, phone = :phone, address = :address \n" + "where user_id = :userId", nativeQuery = true)
    void updateUser(@Param("userName") String userName, @Param("realName") String realName, @Param("phone") String phone, @Param("address") String address, @Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query(value =  "update users \n" + "set pass_word = :passWord \n" + "where user_id = :userId", nativeQuery = true)
    void updatePassword (@Param("passWord") String passWord, @Param("userId") Long userId);

    @Query(value = "insert into users(user_name, real_name, pass_word, address, phone) values (:userName, :realName, :passWord, :address, :phone)", nativeQuery = true)
    Optional<User> addUser(@Param("userName") String userName, @Param("realName") String realName, @Param("passWord") String passWord, @Param("address") String address, @Param("phone") String phone);

    public default CustomUserDetails signup(SignUpRequestDTO signUpRequestDTO) {
        CustomUserDetails newUser = CustomUserDetails.builder().user(User.builder().user_name(signUpRequestDTO.getUserName()).real_name(signUpRequestDTO.getRealName()).role(Role.USER).phone(signUpRequestDTO.getPhone()).address(signUpRequestDTO.getAddress()).pass_word(signUpRequestDTO.getPassWord()).build()).build();
//        CustomUserDetails newUser = CustomUserDetails.builder().build().setUser(User.builder().user_name(signUpRequestDTO.getUserName()).real_name(signUpRequestDTO.getRealName()).role("USER").phone(signUpRequestDTO.getPhone()).address(signUpRequestDTO.getAddress()).pass_word(signUpRequestDTO.getPassWord()).build();
//                .user(User.builder().user_name(signUpRequestDTO.getUserName()).real_name(signUpRequestDTO.getRealName()).role("USER").phone(signUpRequestDTO.getPhone()).address(signUpRequestDTO.getAddress()).pass_word(signUpRequestDTO.getPassWord()).build()).build();
        this.save(newUser.getUser());
        return newUser;
    }
     public default CustomUserDetails login (LoginRequestDTO loginRequestDTO){
        Optional<User> loginUser = this.findByName(loginRequestDTO.getUserName());
        System.out.println(loginUser.get().getUser_name());
        System.out.println(loginRequestDTO.getUserName());
         System.out.println(loginUser.get().getPass_word());
         System.out.println(loginRequestDTO.getPassWord());
//        if(loginUser.get().getUser_name().equals(loginRequestDTO.getUserName()) && loginUser.get().getPass_word().equals(loginRequestDTO.getPassWord())){
//            System.out.println("Thanh cong");
//            return CustomUserDetails.builder().user(User.builder().user_name(loginUser.get().getUser_name()).real_name(loginUser.get().getReal_name()).role(loginUser.get().getRole()).phone(loginUser.get().getPhone()).address(loginUser.get().getAddress()).pass_word(loginUser.get().getPass_word()).build()).build();
//        }
         return CustomUserDetails.builder().user(User.builder().userId(loginUser.get().getUserId()).user_name(loginUser.get().getUser_name()).real_name(loginUser.get().getReal_name()).role(loginUser.get().getRole()).phone(loginUser.get().getPhone()).address(loginUser.get().getAddress()).pass_word(loginUser.get().getPass_word()).build()).build();
//        return null;
     }
}
