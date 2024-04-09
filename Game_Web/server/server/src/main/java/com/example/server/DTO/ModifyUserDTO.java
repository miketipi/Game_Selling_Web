package com.example.server.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModifyUserDTO {
    private String userName;
    private String realName;
    private String phone;
    private String address;
    private Long userId;
    private String passWord;
}
