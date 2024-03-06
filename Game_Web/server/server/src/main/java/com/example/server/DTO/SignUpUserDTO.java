package com.example.server.DTO;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpUserDTO {
    private String userName;
    private String realName;
    private String address;
    private String passWord;
    private String phone;
}
