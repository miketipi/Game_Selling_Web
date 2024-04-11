package com.example.server.Service;

import com.example.server.DTO.*;
import com.example.server.Models.Comments;
import com.example.server.Models.CustomUserDetails;
import com.example.server.Models.Role;
import com.example.server.Models.User;
import com.example.server.Repository.CommentsRepository;
import com.example.server.Repository.UserRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CommentsRepository commentsRepository;

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findUserByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User a = userRepository.findByName(username).get();
        return new CustomUserDetails(a);
    }

    @Override
    public SignUpResponseDTO login(LoginRequestDTO loginRequestDTO) {
//        String unCryptedPass = loginRequestDTO.getPassWord();
//        loginRequestDTO.setPassWord(passwordEncoder.encode(unCryptedPass));
        CustomUserDetails a = userRepository.login(loginRequestDTO);
        if (a == null) return null;
        var jwt = jwtService.generateToken(a);
        return SignUpResponseDTO.builder()
                .id(a.getUser().getUserId())
                .userName(a.getUsername())
                .role(a.getUser().getRole())
                .token(jwt)
                .token_type("Bearer")
                .expire_in(jwtService.extractExpiration(jwt))
                .build();
    }

    @Override
    public Optional<User> getMyInformation(String jwt) {
        if (!jwtService.isValidateToken(jwt)) return null;
        String username = jwtService.extractUserName(jwt);
        Optional<User> me = userRepository.findByName(username);
        return me;
    }

    @Override
    public List<Comments> getAllCommentsByUser(String jwt) {
        if (!jwtService.isValidateToken(jwt) || jwtService.isTokenExpired(jwt)) return null;
        String username = jwtService.extractUserName(jwt);
        Optional<User> me = userRepository.findByName(username);
        List<Comments> ourComments = commentsRepository.getCommentsByUser(me.get().getUserId());
        return ourComments;
    }

    @Override
    public Boolean updateUser(ModifyUserDTO modifyUserDTO, String jwt) {
        //xac nhan jwt va nguoi dung
        if (!jwtService.isValidateToken(jwt) || jwtService.isTokenExpired(jwt)) return false;
        String username = jwtService.extractUserName(jwt);
        Optional<User> me = userRepository.findByName(username);
        if (me.isEmpty() == true) return false;
        else {
//        Boi vi tren frontend se lay trong redux store(userState) ra nen la
            userRepository.updateUser(modifyUserDTO.getUserName(), modifyUserDTO.getRealName(), modifyUserDTO.getPhone(), modifyUserDTO.getAddress(), modifyUserDTO.getUserId());
            return true;
        }
    }

    @Override
    public Boolean updateUser(ModifyUserDTO modifyUserDTO) {
        //xac nhan jwt va nguoi dung
        Optional<User> newInformation = userRepository.findByName(modifyUserDTO.getUserName());
        //    System.out.println(newInformation.get().getUser_name());
        if (!newInformation.isEmpty()) return false;
        // System.out.println(newInformation.get().getUser_name());
//        Boi vi tren frontend se lay trong redux store(userState) ra nen la
        userRepository.updateUser(modifyUserDTO.getUserName(), modifyUserDTO.getRealName(), modifyUserDTO.getPhone(), modifyUserDTO.getAddress(), modifyUserDTO.getUserId());
        return true;
    }

    @Override
    public Boolean updatePassword(UpdatePasswordDTO updatePasswordDTO, String jwt) {
        if (!jwtService.isValidateToken(jwt) || jwtService.isTokenExpired(jwt)) return false;
        String username = jwtService.extractUserName(jwt);
        Optional<User> me = userRepository.findByName(username);
        if (me.isEmpty() == true) return false;
        if (me.get().getPass_word().equals(passwordEncoder.encode(updatePasswordDTO.getNewPassword()))) return false;
        userRepository.updatePassword(passwordEncoder.encode(updatePasswordDTO.getNewPassword()), updatePasswordDTO.getUserId());
        return true;
    }

    //Transactional trong 1 ham lon se phai thuc hien thanh cong hoac chay het 1 vong moi duoc commit
//Truong hop cap nhat user van cap nhat duoc vi transaction chua he thay doi password va username, nen van co the authenticate dc
//    Truong hop cap nhat mat khau thi do transactional chua commit thay doi tren tang code => do do khi lay thong tin no van lay pass cu trong khi authenticate thi su dung pass moi da dc cap nhat duoi csdl
//    Transactional tang code ko lien quan voi thong tin da dc cap nhat duoi db
    @Override
    public Boolean updatePassword(UpdatePasswordDTO updatePasswordDTO) {
//        System.out.println(updatePasswordDTO.getUserId());
        Optional<User> fromDb = userRepository.findById(updatePasswordDTO.getUserId());
        System.out.println("User Service1 : " + fromDb.get().getPass_word());
//        System.out.println(fromDb.get().getUser_name());
//        System.out.println(fromDb.get().getPass_word());
//        System.out.println(passwordEncoder.encode(updatePasswordDTO.getOldPassword()));
//        System.out.println(updatePasswordDTO.getOldPassword());
//        if (fromDb.get().getPass_word().equals(passwordEncoder.encode(updatePasswordDTO.getOldPassword()))) {
//            if (!fromDb.get().getPass_word().equals(passwordEncoder.encode(updatePasswordDTO.getNewPassword()))) {
        userRepository.updatePassword(passwordEncoder.encode(updatePasswordDTO.getNewPassword()), updatePasswordDTO.getUserId());
        Optional<User> fromDbb = userRepository.findById(updatePasswordDTO.getUserId());
        System.out.println("User Service : " + fromDbb.get().getPass_word());
        return true;
//            }
//        }
//        return false;
    }

    @Override
    public void softDelete(Long id) {
        userRepository.softDelete(id);
    }

    @Override
    public Boolean addUser(AddUserDTO addUserDTO) {
        System.out.println("089797");
        System.out.println(addUserDTO.getUserName());
        Optional<User> check  = userRepository.findByName(addUserDTO.getUserName());
        System.out.println(addUserDTO.getPassWord());
        if (check.isEmpty()){
            userRepository.addUser(addUserDTO.getUserName(), addUserDTO.getRealName(), passwordEncoder.encode(addUserDTO.getPassWord()), addUserDTO.getAddress(), addUserDTO.getPhone());
            return true;
        }
        return false;
    }

    @Override
    public SignUpResponseDTO createNewUser(SignUpRequestDTO signUpRequestDTO) {
        String passWord = signUpRequestDTO.getPassWord();
        signUpRequestDTO.setPassWord(passwordEncoder.encode(passWord));
        CustomUserDetails a = userRepository.signup(signUpRequestDTO);
        if (a == null) return null;
        var jwt = jwtService.generateToken(a);
        return SignUpResponseDTO.builder()
                .id(a.getUser().getUserId())
                .userName(a.getUsername())
                .role(Role.USER)
                .token(jwt)
                .token_type("Bearer")
                .expire_in(jwtService.extractExpiration(jwt))
                .build();
    }


}
