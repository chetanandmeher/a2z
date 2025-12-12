package com.a2z.service;

import com.a2z.model.CartItem;
import com.a2z.repository.CartItemRepository;

/**
 * @author Chetanand Meher
 */
public interface CartItemService {

    CartItem updateCartItemQuantity(Long cartItemId, CartItem cartItem, Long userId) throws Exception;

    void removeCartItem(Long cartItemId, Long userId) throws Exception;

    CartItem findCartItemById(Long id) throws Exception;
}
