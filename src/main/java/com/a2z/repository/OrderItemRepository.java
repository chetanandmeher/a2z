package com.a2z.repository;

import com.a2z.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Chetanand Meher
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
