package com.example.server.DTO;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatePasswordDTO {
    private String oldPassword;
    private String newPassword;
    private Long userId;
}
