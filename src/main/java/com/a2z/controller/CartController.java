package com.a2z.controller;


import com.a2z.model.Cart;
import com.a2z.model.CartItem;
import com.a2z.model.Product;
import com.a2z.request.AddCartItemRequest;
import com.a2z.response.ApiResponse;
import com.a2z.service.CartItemService;
import com.a2z.service.CartService;
import com.a2z.service.ProductService;
import com.a2z.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Chetanand Meher
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/carts")
public class CartController {

    private final UserService userService;
    private final ProductService productService;
    private final CartService cartService;
    private final CartItemService cartItemService;


    @GetMapping
    public ResponseEntity<Cart> getUserCart(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        Long userId = userService.getUserIdFromJwt(jwt);

        Cart cart = cartService.findCartByUserId(userId);

        return ResponseEntity.ok(cart);
    }

    @PutMapping("/add-items")
    public ResponseEntity<CartItem> addItemToCart(
            @RequestHeader("Authorization") String jwt,
            @RequestBody AddCartItemRequest request
    ) throws Exception {
        Long userId = userService.getUserIdFromJwt(jwt);

        Product product = productService.findProductById(request.getProductId());
        CartItem cartItem = cartService.addCartItem(userId, product, request.getSize(), request.getQuantity());
        return ResponseEntity.ok(cartItem);
    }

    @DeleteMapping("/items/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long cartItemId
    ) throws Exception {
        Long userId = userService.getUserIdFromJwt(jwt);
        cartItemService.removeCartItem(cartItemId, userId);

        ApiResponse response = ApiResponse.builder()
                .message("Cart item removed successfully")
                .build();

        return ResponseEntity.accepted().body(response);
    }

    @PutMapping("/items/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItem(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long cartItemId,
            @RequestBody CartItem cartItemUpdateRequest
    ) throws Exception {
        Long userId = userService.getUserIdFromJwt(jwt);
        CartItem cartItem = cartItemService.updateCartItemQuantity(cartItemId, cartItemUpdateRequest, userId);
        return ResponseEntity.ok(cartItem);
    }







}
