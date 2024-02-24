package com.example.server.Models;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(CartUserId.class)
@Entity
@Table(name = "cart_item")
public class CartItem {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "cart_id")
    private Long cartId;




}
