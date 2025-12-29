package com.a2z.controller;


import com.a2z.enums.PaymentMethod;
import com.a2z.model.*;
import com.a2z.repository.PaymentOrderRepository;
import com.a2z.response.PaymentLinkResponse;
import com.a2z.service.*;
import com.razorpay.PaymentLink;
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
    private final SellerReportService sellerReportService;
    private final PaymentService paymentService;
    private final PaymentOrderRepository paymentOrderRepository;

    @PostMapping()
    public ResponseEntity<PaymentLinkResponse> createOrderHandler(
            @RequestHeader("Authorization") String jwt,
            @RequestParam PaymentMethod paymentMethod,
            @RequestBody Address shippingAddress
            ) throws Exception{
        Long userId = userService.getUserIdFromJwt(jwt);
        Cart cart = cartService.findCartByUserId(userId);
        Set<Order> orders = orderService.createOrder(userId, shippingAddress, cart);
        User user = userService.findUserById(userId);
        PaymentOrder paymentOrder = paymentService.createOrder(user, orders);

        PaymentLinkResponse paymentLinkResponse = new PaymentLinkResponse();
        if(PaymentMethod.RAZORPAY.equals(paymentMethod)){
            PaymentLink paymentLink = paymentService.createRazorpayPaymentLink(
                    user,
                    paymentOrder.getAmount(),
                    paymentOrder.getId());
            String paymentUrl = paymentLink.get("short_url");
            String paymentUrlId = paymentLink.get("id");

            paymentLinkResponse.setPaymentLinkUrl(paymentUrl);

            paymentOrder.setPaymentLinkId(paymentUrlId);
            paymentOrderRepository.save(paymentOrder);
        }


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

        SellerReport sellerReport = sellerReportService.getSellerReportBySellerId(order.getSellerId());
        sellerReport.setCancelledOrders(sellerReport.getCancelledOrders()+1);
        sellerReport.setTotalRefunds(sellerReport.getTotalRefunds() + order.getTotalSellingPrice());


        return ResponseEntity.ok(order);
    }



}
