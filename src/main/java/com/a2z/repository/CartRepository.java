package com.a2z.repository;

import com.a2z.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Chetanand Meher
 */
public interface CartRepository extends JpaRepository<Cart, Long> {
}
