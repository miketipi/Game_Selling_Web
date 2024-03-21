package com.example.server.Controller;

import com.example.server.DTO.SignUpRequestDTO;
import com.example.server.DTO.SignUpResponseDTO;
import com.example.server.Service.JwtService;
import com.example.server.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthController authController;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDTO> signup (@RequestBody SignUpRequestDTO req) {
        SignUpResponseDTO signUpRequestDTO = userService.createNewUser(req);
        return ResponseEntity.ok(signUpRequestDTO);
    }
}
