package com.a2z.controller;


import com.a2z.model.Cart;
import com.a2z.model.Coupon;
import com.a2z.model.User;
import com.a2z.service.CartService;
import com.a2z.service.CouponService;
import com.a2z.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Chetanand Meher
 */
@RestController
@RequiredArgsConstructor
public class AdminCouponController {
    private final CouponService couponService;
    private final UserService userService;
    private final CartService cartService;

    @PostMapping("/api/coupons/apply")
    public ResponseEntity<Cart> applyCoupon(
            @RequestParam String apply,
            @RequestParam String code,
            @RequestParam double orderValue,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        Long userId = userService.getUserIdFromJwt(jwt);
        User user = userService.findUserById(userId);
        Cart cart;

        if("true".equals(apply)){
            cart = couponService.applyCoupon(code, orderValue, userId);
        } else {
            cart = couponService.removeCoupon(code, user.getId());
        }
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/api/admin/coupons/delete/{couponId}")
    public ResponseEntity<String> deleteCoupon(@PathVariable Long couponId) {
        couponService.deleteCoupon(couponId);
        return ResponseEntity.ok("Coupon deleted successfully");
    }

    @GetMapping("/api/admin/coupons/all")
    public ResponseEntity<Iterable<Coupon>> getAllCoupons() {
        Iterable<Coupon> coupons = couponService.findAllCoupon();
        return ResponseEntity.ok(coupons);
    }



}
