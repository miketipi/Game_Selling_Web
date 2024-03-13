package com.example.server.DTO;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ModifyGameDTO {
    private Long productId;
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
