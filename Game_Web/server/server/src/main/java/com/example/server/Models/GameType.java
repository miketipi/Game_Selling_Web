package com.example.server.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gametype")
public class GameType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_type_id")
    private Long gameTypeId;
    @Column(name = "game_type_name")
    private String gameTypeName;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "modified_at")
    private Date modifiedAt;
    @Column(name = "deleted")
    private Boolean deleted;
}
