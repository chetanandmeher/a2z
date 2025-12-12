package com.a2z.service;

import com.a2z.model.Cart;
import com.a2z.model.CartItem;
import com.a2z.model.Product;
import com.a2z.model.User;

public interface CartService {

    public CartItem addCartItem(
            Long userId,
            Product product,
            String size,
            int quantity
    );

    public Cart findUserCart(User user);

    public Cart findCartByUserId(Long userId);
}
