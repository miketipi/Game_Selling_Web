package com.example.server.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(FavouriteProductId.class)
@Entity
@Table(name = "fav_item")
public class FavouriteItem {
    @Id
    @Column(name = "product_id")
    private Long productId;
    @Id
    @Column(name = "fav_id")
    private Long favId;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "modified_at")
    private Date modifiedAt;
    @Column(name = "deleted")
    private Boolean deleted;

    public FavouriteItem(Long favId, Long productId){
        this.favId = favId;
        this.productId = productId;
    }
}
