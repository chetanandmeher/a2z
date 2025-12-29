package com.a2z.service.impl;

import com.a2z.enums.PaymentOrderStatus;
import com.a2z.enums.PaymentStatus;
import com.a2z.model.Order;
import com.a2z.model.PaymentOrder;
import com.a2z.model.User;
import com.a2z.repository.OrderRepository;
import com.a2z.repository.PaymentOrderRepository;
import com.a2z.service.PaymentService;
import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author Chetanand Meher
 */
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private PaymentOrderRepository paymentOrderRepository;
    private OrderRepository orderRepository;

    private String apiKey = "apikey";
    private String apiSecret = "apisecret";


    @Override
    public PaymentOrder createOrder(User user, Set<Order> orders) {
        Long amount = orders.stream().mapToLong(Order::getTotalSellingPrice).sum();

        PaymentOrder paymentOrder = PaymentOrder.builder()
                .amount(amount)
                .user(user)
                .orders(orders)
                .build();

        return paymentOrderRepository.save(paymentOrder);
    }


    @Override
    public PaymentOrder getPaymentOrderByOrderId(Long orderId) throws Exception {
        return paymentOrderRepository.findById(orderId).orElseThrow(() ->
                new Exception("Payment Order not found")
        );
    }

    @Override
    public PaymentOrder getPaymentOrderByPaymentId(String paymentId) throws Exception {
        PaymentOrder paymentOrder = paymentOrderRepository.findByPaymentLinkId(paymentId);
        if (paymentOrder == null) {
            throw new Exception("Payment Order not found with provided payment link id");
        }
        return paymentOrder;
    }

    @Override
    public Boolean proceedPaymentOrder(PaymentOrder paymentOrder, String paymentId, String paymentLinkId) throws RazorpayException {

        if(PaymentOrderStatus.PENDING.equals(paymentOrder.getStatus())){
            RazorpayClient razorpayClient = new RazorpayClient(apiKey, apiSecret);

            Payment payment = razorpayClient.payments.fetch(paymentId);

            String status = payment.get("status");
            if("captured".equals(status)){
                Set<Order> orders = paymentOrder.getOrders();
                for(Order order : orders) {
                    order.setPaymentStatus(PaymentStatus.COMPLETED);
                    orderRepository.save(order);
                }
                paymentOrder.setStatus(PaymentOrderStatus.SUCCESS);
                paymentOrderRepository.save(paymentOrder);
                return true;
            }
            else {
                paymentOrder.setStatus(PaymentOrderStatus.FAILED);
                paymentOrderRepository.save(paymentOrder);
                return false;
            }
        }
        return false;
    }

    @Override
    public PaymentLink createRazorpayPaymentLink(User user, Long amount, long orderId) {
            amount = amount*100;

            try{
                RazorpayClient razorpayClient = new RazorpayClient(apiKey, apiSecret);

                JSONObject paymentLinkRequest = new JSONObject();
                paymentLinkRequest.put("amount", amount);
                paymentLinkRequest.put("currency", "INR");

                JSONObject customer = new JSONObject();
                customer.put("name", user.getFirstName() + " " + user.getLastName());
                customer.put("email", user.getEmail());
                paymentLinkRequest.put("customer", customer);

                JSONObject notify = new JSONObject();
                notify.put("email", true);
                paymentLinkRequest.put("notify", notify);

                paymentLinkRequest.put("callback_url",
                        "http://localhost:3000/payment-success"+orderId);
                paymentLinkRequest.put("callback_method", "get");

                PaymentLink paymentLink = razorpayClient.paymentLink.create(paymentLinkRequest);

                String paymentLinkUrl = paymentLink.get("short_url");
                String paymentLinkId = paymentLink.get("id");

                return paymentLink;

            } catch (RazorpayException e) {
                throw new RuntimeException(e.getMessage());
            }
        }


}
