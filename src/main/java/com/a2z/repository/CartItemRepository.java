package com.a2z.repository;

import com.a2z.model.Cart;
import com.a2z.model.CartItem;
import com.a2z.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Chetanand Meher
 */
public interface CartItemRepository extends JpaRepository<CartItem, Long> {


    CartItem findByCartAndProductAndSize(Cart cart, Product product, String size);
}
