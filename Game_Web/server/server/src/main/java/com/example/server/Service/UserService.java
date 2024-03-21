package com.example.server.Service;

import com.example.server.DTO.SignUpRequestDTO;
import com.example.server.DTO.SignUpResponseDTO;
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
}
