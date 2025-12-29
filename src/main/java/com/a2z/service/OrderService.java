package com.a2z.service;

import com.a2z.enums.OrderStatus;
import com.a2z.model.Address;
import com.a2z.model.Cart;
import com.a2z.model.Order;
import com.a2z.model.OrderItem;

import java.util.List;
import java.util.Set;

/**
 * @author Chetanand Meher
 */
public interface OrderService {
     Set<Order> createOrder(Long userId, Address shippingAddress, Cart cart) throws Exception;
     Order findOrderById(Long orderId) throws Exception;
     List<Order> getUserOrderHistory(Long userId);
     List<Order> getSellerOrdersBySellerId(Long sellerId);
     Order  updateOrderStatus(Long orderId, OrderStatus orderStatus) throws Exception;
     Order cancleOrder(Long orderId, Long userId) throws Exception;
     OrderItem findOrderItemByOrderItemId(Long orderItemId) throws Exception;

}

