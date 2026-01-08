package com.a2z.service;

import com.a2z.model.Cart;
import com.a2z.model.Coupon;
import com.a2z.model.User;

import java.util.List;

public interface CouponService {

    Cart applyCoupon(String code, double orderValue, Long  userId) throws Exception;
    Cart removeCoupon(String code, Long userId);
    Coupon findCouponById(Long couponId);
    Coupon createCoupon(Coupon coupon);
    List<Coupon> findAllCoupon();
    void deleteCoupon(Long couponId);
}
