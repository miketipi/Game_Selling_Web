package com.example.server.Models;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "user_name")
    private String user_name;
    @Column(name = "real_name")
    private String real_name;
    @Column (name = "pass_word")
    private String pass_word;
    @Column (name = "address")
    private String address;
    @Column (name = "phone")
    private String phone;
    @Column (name ="role")
    private String role = "USER";
}
