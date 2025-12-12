package com.a2z.controller;


import com.a2z.enums.PaymentMethod;
import com.a2z.model.*;
import com.a2z.response.PaymentLinkResponse;
import com.a2z.service.CartService;
import com.a2z.service.OrderService;
import com.a2z.service.SellerService;
import com.a2z.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * @author Chetanand Meher
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final UserService userService;
    private final CartService cartService;
    private final OrderService orderService;
    private final SellerService sellerService;

    @PostMapping()
    public ResponseEntity<PaymentLinkResponse> createOrderHandler(
            @RequestHeader("Authorization") String jwt,
            @RequestParam PaymentMethod paymentMethod,
            @RequestBody Address shippingAddress
            ) throws Exception{
        Long userId = userService.getUserIdFromJwt(jwt);
        Cart cart = cartService.findCartByUserId(userId);
        Set<Order> orders = orderService.createOrder(userId, shippingAddress, cart);

        PaymentLinkResponse paymentLinkResponse = new PaymentLinkResponse();
        return ResponseEntity.ok(paymentLinkResponse);

    }

    @GetMapping("/users/order-history")
    public ResponseEntity<List<Order>> usersOrderHistoryHandler(
            @RequestHeader("Authorization") String jwt
    ) throws Exception{
        Long userId = userService.getUserIdFromJwt(jwt);
        List<Order> orders = orderService.getUserOrderHistory(userId);
        return ResponseEntity.accepted().body(orders);
    }

    @GetMapping("{orderId}")
    public ResponseEntity<Order> getOrderById(
            @PathVariable("orderId") Long orderId,
            @RequestHeader("Authorization") String jwt) throws Exception{

        Long userId = userService.getUserIdFromJwt(jwt);
        Order order = orderService.findOrderById(orderId);
        if(!order.getUser().getId().equals(userId)){
            throw new Exception("You cannot access this order");
        }
        return ResponseEntity.ok(order);
    }

    @GetMapping("/items/{orderItemId}")
    public ResponseEntity<OrderItem> getOrderItemById(
            @RequestHeader("Authorization")String jwt,
            @PathVariable("orderItemId") Long orderItemId
    ) throws Exception{
        Long userId = userService.getUserIdFromJwt(jwt);
        OrderItem orderItem = orderService.findOrderItemByOrderItemId(orderItemId);
        if(!orderItem.getUserId().equals(userId)){
            throw new Exception("You cannot access this order");
        }
        return ResponseEntity.ok(orderItem);
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Order> cancelOrder(
            @RequestHeader("Authorization") String jwt,
            @PathVariable("orderId") Long orderId
    ) throws Exception{
        long userId = userService.getUserIdFromJwt(jwt);
        Order order = orderService.findOrderById(orderId);
        if(!order.getUser().getId().equals(userId)) {
            throw new Exception("You do not have access to this order");
        }

        return ResponseEntity.ok(order);
    }



}
