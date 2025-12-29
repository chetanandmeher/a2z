package com.a2z.service.impl;

import com.a2z.enums.OrderStatus;
import com.a2z.enums.PaymentStatus;
import com.a2z.model.*;
import com.a2z.repository.AddressRepository;
import com.a2z.repository.OrderItemRepository;
import com.a2z.repository.OrderRepository;
import com.a2z.service.OrderService;
import com.a2z.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Chetanand Meher
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final AddressRepository addressRepository;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public Set<Order> createOrder(Long userId, Address shippingAddress, Cart cart) throws Exception {
        User user = userService.findUserById(userId);

        if(!user.getAddresses().contains(shippingAddress)){
            user.getAddresses().add(shippingAddress);
        }
        Address address = addressRepository.save(shippingAddress);

        // Map all the cart item in key value pair with seller id
        Map<Long, List<CartItem>> itemsBySeller =
                cart.getCartItems().stream().collect(
                        Collectors.groupingBy(item->item.getProduct().getSeller().getId()));

        // Create Set of order for different sellers
        Set<Order> orders = new HashSet<>();

        // to group the items by seller id
        for(Map.Entry<Long, List<CartItem>> entry : itemsBySeller.entrySet()){
            Long sellerId = entry.getKey();
            List<CartItem> items = entry.getValue();

            // calculate the order price
            int totalOrderPrice = items.stream().mapToInt(CartItem::getSellingPrice).sum();
            int totalItems = items.stream().mapToInt(CartItem::getQuantity).sum();

            Order createdOrder = Order.builder()
                    .user(user)
                    .sellerId(sellerId)
                    .totalMrpPrice(totalOrderPrice)
                    .totalSellingPrice(totalOrderPrice)
                    .totalItems(totalItems)
                    .shippingAddress(address)
                    .orderStatus(OrderStatus.PENDING)
                    .paymentStatus(PaymentStatus.PENDING)
                    .build();

            // to create orderItems as per order from cartItems and add to the order
            // create order items list and add it to Order

            List<OrderItem> orderItems = new ArrayList<>();
            for(CartItem item: items) {
                OrderItem orderItem = OrderItem.builder()
                        .order(createdOrder)
                        .mrpPrice(item.getMrpPrice())
                        .sellingPrice(item.getSellingPrice())
                        .product(item.getProduct())
                        .quantity(item.getQuantity())
                        .size(item.getSize())
                        .userId(item.getUserId())
                        .build();

                OrderItem savedOrderItem = orderItemRepository.save(orderItem);
                orderItems.add(orderItem);
            }
            createdOrder.setOrderItems(orderItems);

            Order savedOrder = orderRepository.save(createdOrder);

            orders.add(savedOrder);
        }
        return orders;

    }


    @Override
    public Order findOrderById(Long orderId) throws Exception {
        return orderRepository.findById(orderId).orElseThrow(() -> new Exception("Order not found!!"));
    }

    @Override
    public List<Order> getUserOrderHistory(Long userId) {
        return orderRepository.getAllByUserId(userId);
    }

    @Override
    public List<Order> getSellerOrdersBySellerId(Long sellerId) {
        return orderRepository.getAllBySellerId(sellerId);

    }

    @Override
    public Order updateOrderStatus(Long orderId, OrderStatus orderStatus) throws Exception {
        Order order  = findOrderById(orderId);
        order.setOrderStatus(orderStatus);
        return orderRepository.save(order);
    }

    @Override
    public Order cancleOrder(Long orderId, Long userId) throws Exception {
        Order order = findOrderById(orderId);
        if(!userId.equals(order.getUser().getId())){
            throw new Exception("You don't have access to this order.");
        }
        order.setOrderStatus(OrderStatus.CANCELLED);
        return orderRepository.save(order);
    }

    @Override
    public OrderItem findOrderItemByOrderItemId(Long orderItemId) throws Exception {
        return orderItemRepository.findById(orderItemId).orElseThrow(()-> new Exception("Order item do not exist...."));

    }
}
