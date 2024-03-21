package com.example.server.DTO;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDTO {
    private String userName;
    private String realName;
    private String passWord;
    private String address;
    private String phone;
}
