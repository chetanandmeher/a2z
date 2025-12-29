package com.a2z.controller;


import com.a2z.enums.OrderStatus;
import com.a2z.exception.SellerException;
import com.a2z.model.Order;
import com.a2z.model.Seller;
import com.a2z.service.OrderService;
import com.a2z.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sellers/orders")
public class SellerOrderController {

    private final OrderService orderService;
    private final SellerService sellerService;

    @GetMapping()
    public ResponseEntity<List<Order>> getAllOrderHandler(
            @RequestHeader("Authorization") String jwt
    ) throws SellerException {
        Seller seller = sellerService.getSellerProfileByJwt(jwt);
        List<Order> orders = orderService.getSellerOrdersBySellerId(seller.getId());

        return ResponseEntity.accepted().body(orders);
    }

    @PatchMapping("/{orderId}/status/{orderStatus}")
    public ResponseEntity<Order> updateOrderStatusHandler(
            @RequestHeader("Authorization") String jwt,
            @PathVariable("orderId") Long orderId,
            @PathVariable("orderStatus")OrderStatus orderStatus
            ) throws Exception{

        Order order = orderService.updateOrderStatus(orderId, orderStatus);
        return ResponseEntity.accepted().body(order);
    }



}
