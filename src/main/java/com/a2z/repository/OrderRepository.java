package com.a2z.repository;

import com.a2z.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Chetanand Meher
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> getAllByUserId(Long userId);
    List<Order> getAllBySellerId(Long sellerId);
}
