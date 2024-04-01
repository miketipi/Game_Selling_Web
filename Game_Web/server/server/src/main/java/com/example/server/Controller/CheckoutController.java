package com.example.server.Controller;

import com.example.server.DTO.CheckoutInfoDTO;
import com.example.server.Service.VNPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {
    @Autowired
    private VNPayService vnPayService;

    //Request body nen them 1 doi tuong bao gom nhieu doi tuong nho hon, chu khong don 1 luc nhieu bien, chi truyen 1 DTO
    @PostMapping("/checkout")
    void checkOut(@RequestBody CheckoutInfoDTO checkoutInfoDTO) throws UnsupportedEncodingException {
        vnPayService.createOrderUrl(checkoutInfoDTO.getTotal(), checkoutInfoDTO.getOrderInfo(),checkoutInfoDTO.getReturnUrl());
    }
}
