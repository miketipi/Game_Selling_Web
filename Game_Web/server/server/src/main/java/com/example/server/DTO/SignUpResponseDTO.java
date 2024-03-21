package com.example.server.DTO;

import com.example.server.Models.Role;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpResponseDTO {
private Long id;
private String userName;
private Role role;
private String token;
private String token_type;
private Date expire_in;
}
