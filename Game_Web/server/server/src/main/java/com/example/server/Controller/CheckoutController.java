package com.example.server.Controller;

import com.example.server.DTO.CheckoutInfoDTO;
import com.example.server.DTO.UpdateOrderDTO;
import com.example.server.DTO.VNPaymentReturnDTO;
import com.example.server.Service.CartService;
import com.example.server.Service.JwtService;
import com.example.server.Service.OrderService;
import com.example.server.Service.VNPayService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {
    @Autowired
    private VNPayService vnPayService;

    @Autowired
    private CartService cartService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private OrderService orderService;

//    @Value("${STRIPE_PUBLIC_KEY}")
//    private String stripePublicKey;

    
    //Request body nen them 1 doi tuong bao gom nhieu doi tuong nho hon, chu khong don 1 luc nhieu bien, chi truyen 1 DTO
    @PostMapping("/checkout")
    void checkOut(@RequestBody CheckoutInfoDTO checkoutInfoDTO) throws UnsupportedEncodingException {
        vnPayService.createOrderUrl(checkoutInfoDTO.getTotal(), checkoutInfoDTO.getOrderInfo(), checkoutInfoDTO.getReturnUrl());
    }
    //Se co mot yeu cau tu frontend xuong nham yeu cau xac nhan thong tin va cap nhat vao co so du lieu

    @PostMapping("/vnpay/return")
    public ResponseEntity<VNPaymentReturnDTO> paymentStatus(@RequestHeader("Authorization") String header, HttpServletRequest request, @RequestBody UpdateOrderDTO updateOrderDTO) {
        int paymentStatus = vnPayService.orderReturn(request);
        String orderInfo = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPrice = request.getParameter("vnp_Amount");
        VNPaymentReturnDTO res = VNPaymentReturnDTO.builder().orderId(orderInfo).totalMoney(totalPrice).txtn(transactionId).paymentStatus(paymentStatus == 1 ? true : false).txtnTime(paymentTime).build();
        updateOrderDTO.setOrderStatus(paymentStatus == 1 ? true : false);
        String jwt = header.substring(7);
        String username = jwtService.extractUserName(jwt);
        if (cartService.deleteCartByName(username)) {
            if (orderService.updateOrderStatus(updateOrderDTO.getId())) {
                res.setUpdateDB(true);
            } else {
                res.setUpdateDB(false);
            }
            return ResponseEntity.ok(res);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
