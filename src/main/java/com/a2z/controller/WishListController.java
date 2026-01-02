package com.a2z.controller;

import com.a2z.model.User;
import com.a2z.model.WishList;
import com.a2z.service.UserService;
import com.a2z.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Chetanand Meher
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wishlists")
public class WishListController {

    private final UserService userService;
    private final WishListService wishListService;

    @GetMapping()
    public ResponseEntity<WishList> getWishList(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        // Implementation goes here
        Long userId = userService.getUserIdFromJwt(jwt);
        WishList wishList = wishListService.getWishListByUserId(userId);
        return ResponseEntity.ok(wishList);
    }

    @PostMapping("/add-product/{productId}")
    public ResponseEntity<WishList> addProductToWishList(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long productId
    ) throws Exception {
        Long userId = userService.getUserIdFromJwt(jwt);
        WishList updatedWishList = wishListService.addProductToWishList(userId, productId);
        return ResponseEntity.ok(updatedWishList);
    }

}

