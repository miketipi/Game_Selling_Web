package com.example.server.Controller;

import com.example.server.DTO.LoginRequestDTO;
import com.example.server.DTO.SignUpRequestDTO;
import com.example.server.DTO.SignUpResponseDTO;
import com.example.server.Service.JwtService;
import com.example.server.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

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

    public static final Logger logger = Logger.getLogger("Authenticate");
    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDTO> signup (@RequestBody SignUpRequestDTO req) {
        logger.info("Bat dau qua trinh signup");
        SignUpResponseDTO signUpRequestDTO = userService.createNewUser(req);
        logger.info("Signup thanh cong");
        return ResponseEntity.ok(signUpRequestDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<SignUpResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
    SignUpResponseDTO LoginResponseDTO = userService.login(loginRequestDTO);
    return ResponseEntity.ok(LoginResponseDTO);
    }
}
