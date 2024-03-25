package com.example.server.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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
    @Column(name = "product_id")
    private Long productId;

    @Id
    @Column(name = "cart_id")
    private Long cartId;

    @Column(name = "own_price")
    private Long ownPrice;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "modified_at")
    private Date modifiedAt;

    @Column(name = "deleted")
    private Boolean deleted;





}
