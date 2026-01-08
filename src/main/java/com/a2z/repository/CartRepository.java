package com.a2z.repository;

import com.a2z.model.Cart;
import com.a2z.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Chetanand Meher
 */
public interface CartRepository extends JpaRepository<Cart, Long> {

    public Cart findByUserId(Long customerId);
    public Cart findByUser(User user);
}
