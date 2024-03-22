package com.example.server.DTO;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OtherUserDTO {
    private String userName;
    private String address;
    private String phone;
}
