package com.example.server.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VNPaymentReturnDTO {
    private Boolean paymentStatus;
    private  String orderId;
    private String totalMoney;
    private String txtnTime;
    private Boolean updateDB;
    private String txtn;
}
