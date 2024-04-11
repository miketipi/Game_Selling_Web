package com.example.server.Service;

import com.example.server.DTO.*;
import com.example.server.Models.Comments;
import com.example.server.Models.CustomUserDetails;
import com.example.server.Models.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService extends UserDetailsService {
    List<User> getAllUser();
    Optional<User> getUserById(Long id);
    Optional<User> findUserByName(String name);
    public SignUpResponseDTO createNewUser (SignUpRequestDTO signUpRequestDTO);
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    public SignUpResponseDTO login(LoginRequestDTO loginRequestDTO);

    Optional<User> getMyInformation(String jwt);

    List<Comments> getAllCommentsByUser(String jwt);
    Boolean updateUser(ModifyUserDTO modifyUserDTO , String jwt);
    Boolean updateUser(ModifyUserDTO modifyUserDTO);
    Boolean updatePassword(UpdatePasswordDTO updatePasswordDTO, String jwt);
    Boolean updatePassword(UpdatePasswordDTO updatePasswordDTO);

    void softDelete(Long id);

    Boolean addUser(AddUserDTO addUserDTO);
}
