package com.example.server.DTO;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InsertGameDTO {
    private String gameName;
    private Long gameTypeId;
    private BigDecimal gamePrice;
    private String gameImage;
    private Float gameRating;
    private String gameStatus;
    private Long platformId;
    private String gameVersion;
    private Integer gameDownloaded;
    private Long publisherId;
}
