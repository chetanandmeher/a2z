package com.a2z.service.impl;


import com.a2z.model.CartItem;
import com.a2z.repository.CartItemRepository;
import com.a2z.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Chetanand Meher
 */
@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, CartItem cartItemUpdateRequest, Long userId) throws Exception {
        CartItem cartItem = findCartItemById(cartItemId);

        Long cartUserId = cartItem.getUserId();
        if(cartUserId.equals(userId)){
            cartItem.setQuantity(cartItemUpdateRequest.getQuantity());
            cartItem.setMrpPrice(cartItem.getProduct().getMrpPrice()*cartItemUpdateRequest.getQuantity());
            cartItem.setSellingPrice(cartItem.getSellingPrice()*cartItemUpdateRequest.getQuantity());
            return cartItemRepository.save(cartItem);
        }
        throw new Exception("Unauthorized access to cart item");
    }

    @Override
    public void removeCartItem(Long cartItemId, Long userId) throws Exception {
        CartItem cartItem = findCartItemById(cartItemId);

        Long cartUserId = cartItem.getUserId();
        if(cartUserId.equals(userId)){
            cartItemRepository.delete(cartItem);
        }else {
            throw new Exception("Unauthorized access to cart item");
        }
    }

    @Override
    public CartItem findCartItemById(Long id) throws Exception {
        return cartItemRepository.findById(id)
                .orElseThrow(() -> new Exception("Cart item not found!!"));
    }
}
