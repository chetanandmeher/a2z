package com.a2z.controller;

import com.a2z.model.*;
import com.a2z.response.ApiResponse;
import com.a2z.response.PaymentLinkResponse;
import com.a2z.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private final UserService userService;
    private final SellerService sellerService;
    private final SellerReportService sellerReportService;
    private final OrderService orderService;
    private final TransactionService transactionService;

    @GetMapping("/{paymentId} ")
    public ResponseEntity<ApiResponse> paymentSuccessHandler(
            @PathVariable String paymentId,
            @PathVariable String paymentLinkId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        Long user = userService.getUserIdFromJwt(jwt);

        PaymentLinkResponse paymentLinkResponse;

        PaymentOrder paymentOrder = paymentService
                .getPaymentOrderByPaymentId(paymentId);

        boolean paymentSuccess = paymentService
                .proceedPaymentOrder(
                        paymentOrder,
                        paymentId,
                        paymentLinkId
                );
        if (paymentSuccess) {
            for (Order order : paymentOrder.getOrders()) {
                transactionService.createTransaction(order);
                Seller seller = sellerService.getSellerById(order.getSellerId());
                SellerReport sellerReport = sellerReportService
                        .getSellerReportBySellerId(seller.getId());
                sellerReport.setTotalOrders(sellerReport.getTotalOrders() + 1);
                sellerReport.setTotalEarnings(sellerReport.getTotalEarnings() + order.getTotalSellingPrice());
                sellerReport.setTotalSales(sellerReport.getTotalSales() + order.getOrderItems().size());
                sellerReportService.updateSellerReport(sellerReport.getId(), sellerReport);

            }
        }
        ApiResponse response = ApiResponse.builder()
                .message("Payment processed successfully")
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
