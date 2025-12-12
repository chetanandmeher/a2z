package com.a2z.service.impl;


import com.a2z.model.Cart;
import com.a2z.model.CartItem;
import com.a2z.model.Product;
import com.a2z.model.User;
import com.a2z.repository.CartItemRepository;
import com.a2z.repository.CartRepository;
import com.a2z.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public CartItem addCartItem(Long userId, Product product, String size, int quantity) {
        Cart cart = findCartByUserId(userId);

        CartItem cartItem = cartItemRepository.findByCartAndProductAndSize(cart, product, size);
        if(cartItem == null){
            CartItem newCartItem = CartItem.builder()
                    .cart(cart)
                    .userId(userId)
                    .product(product)
                    .size(size)
                    .quantity(quantity)
                    .mrpPrice(product.getMrpPrice() * quantity)
                    .sellingPrice(product.getSellingPrice() * quantity)
                    .build();
            cart.getCartItems().add(newCartItem);

            return cartItemRepository.save(newCartItem);
        }
        return cartItem;
    }

    @Override
    public Cart findUserCart(User user) {
        Cart cart = cartRepository.findByUser(user);

        // calculate cart total price
        int totalPrice = 0;
        int totalDiscountPrice = 0;
        int totalItem = 0;

        for(CartItem cartItem: cart.getCartItems()){
            totalPrice += cartItem.getMrpPrice();
            totalDiscountPrice += cartItem.getSellingPrice();
            totalItem += cartItem.getQuantity();
        }

        cart.setTotalItems(totalItem);
        cart.setTotalMrpPrice(totalPrice);
        cart.setTotalSellingPrice(totalDiscountPrice);
        cart.setDiscount(calculateDiscountPercentage(totalPrice, totalDiscountPrice));


        return cartRepository.save(cart);
    }

    @Override
    public Cart findCartByUserId(Long userId) {
        Cart cart = cartRepository.findByUser_Id(userId);
        int totalPrice = 0;
        int totalDiscountPrice = 0;
        int totalItem = 0;

        for(CartItem cartItem: cart.getCartItems()){
            totalPrice += cartItem.getMrpPrice();
            totalDiscountPrice += cartItem.getSellingPrice();
            totalItem += cartItem.getQuantity();
        }

        cart.setTotalItems(totalItem);
        cart.setTotalMrpPrice(totalPrice);
        cart.setTotalSellingPrice(totalDiscountPrice);
        cart.setDiscount(calculateDiscountPercentage(totalPrice, totalDiscountPrice));


        return cartRepository.save(cart);
    }

    private int calculateDiscountPercentage(int mrpPrice, int sellingPrice) {
        if(mrpPrice<=0){
//            throw new IllegalArgumentException("MRP Price must be greater than zero");
            return 0;
        }
        double discount = mrpPrice - sellingPrice;
        return (int) ((discount / mrpPrice) * 100);
    }
}
