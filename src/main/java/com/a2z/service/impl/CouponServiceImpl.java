package com.a2z.service.impl;

import com.a2z.model.Cart;
import com.a2z.model.Coupon;
import com.a2z.model.User;
import com.a2z.repository.CartRepository;
import com.a2z.repository.CouponRepository;
import com.a2z.repository.UserRepository;
import com.a2z.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Chetanand Meher
 */
@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    @Override
    public Cart applyCoupon(String code, double orderValue, Long userId) throws Exception {
        Coupon coupon = couponRepository.findByCode(code);
        Cart cart = cartRepository.findByUserId(userId);

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        if(coupon == null) {
            throw new RuntimeException("Invalid coupon code");
        }
        if(user.getUsedCoupons().contains(coupon)){
            throw new RuntimeException("Coupon already used by the user");
        }
        if(orderValue < coupon.getMinimumPurchaseAmount()) {
            throw new RuntimeException("Order value need to be minimum " + coupon.getMinimumPurchaseAmount() + " to apply this coupon");
        }
        if(coupon.isActive() &&
                LocalDate.now().isAfter(coupon.getValidityStartDate()) &&
                LocalDate.now().isBefore(coupon.getValidityEndDate())
        ){
            user.getUsedCoupons().add(coupon);
            userRepository.save(user);

            double discountedPrice = (cart.getTotalSellingPrice()*coupon.getDiscountPercentage())/100;
            cart.setTotalSellingPrice((int)(cart.getTotalSellingPrice()-discountedPrice));
            cartRepository.save(cart);
        }
        throw new Exception("Invalid coupon!!");

    }

    @Override
    public Cart removeCoupon(String code, Long userId) {
        Coupon  coupon = couponRepository.findByCode(code);

        if(coupon==null){
            throw new RuntimeException("Invalid coupon code");
        }
        Cart cart = cartRepository.findByUserId(userId);
        double discountedPrice = (cart.getTotalSellingPrice()*coupon.getDiscountPercentage())/100;
        cart.setTotalSellingPrice((int)(cart.getTotalSellingPrice()+discountedPrice));
        return cartRepository.save(cart);
    }

    @Override
    public Coupon findCouponById(Long couponId) {
        return couponRepository.findById(couponId).orElseThrow(() -> new RuntimeException("Coupon not found"));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")  
    public Coupon createCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    @Override
    public List<Coupon> findAllCoupon() {
        return couponRepository.findAll();
    }

    @Override
    public void deleteCoupon(Long couponId) {
        
    }
}
