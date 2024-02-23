package com.example.server.Models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "game_type_id")
    private Long gameTypeId;
    @Column(name = "game_name")
    private String gameName;
    @Column (name =  "game_price")
    private BigDecimal gamePrice;
    @Column (name = "game_image")
    private String gameImage;
    @Column (name = "game_rating")
    private Float gameRating;
    @Column (name = "game_status")
    private String gameStatus;
    @Column(name = "platform_id")
    private Long platformId;
    @Column (name = "game_version")
    private String gameVersion;
    @Column (name =  "game_downloaded")
    private Integer gameDownloaded;
    @Column (name = "publisher_id")
    private Long publisherId;
    @Column (name = "deleted")
    private  boolean deleted = false;
    @Column(name = "created_at")
    private Date createdAt;
    @Column (name = "modified_at")
    private Date modifiedAt;


}
