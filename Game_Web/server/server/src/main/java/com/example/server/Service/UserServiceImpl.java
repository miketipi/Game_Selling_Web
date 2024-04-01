package com.example.server.Service;

import com.example.server.DTO.LoginRequestDTO;
import com.example.server.DTO.SignUpRequestDTO;
import com.example.server.DTO.SignUpResponseDTO;
import com.example.server.Models.CustomUserDetails;
import com.example.server.Models.Role;
import com.example.server.Models.User;
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
