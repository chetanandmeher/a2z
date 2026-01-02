package com.a2z.service.impl;

import com.a2z.exception.ProductException;
import com.a2z.model.Product;
import com.a2z.model.User;
import com.a2z.model.WishList;
import com.a2z.repository.WishListRepository;
import com.a2z.service.ProductService;
import com.a2z.service.UserService;
import com.a2z.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Chetanand Meher
 */
@Service
@RequiredArgsConstructor
public class WishListServiceImpl implements WishListService {

    private final WishListRepository wishListRepository;
    private final UserService userService;
    private final ProductService productService;

    @Override
    public WishList createWishList(Long userId) throws Exception {
        User user = userService.findUserById(userId);

        WishList wishList = WishList.builder()
                .user(user)
                .build();

        return wishListRepository.save(wishList);
    }

    @Override
    public WishList getWishListByUserId(Long userId) {
        WishList wishList = wishListRepository.findByUserId(userId);
        if(wishList == null) {
            try {
                wishList = createWishList(userId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return wishList;
    }

    @Override
    public WishList addProductToWishList(Long userId, Long productId) throws ProductException {
        WishList wishList = getWishListByUserId(userId);
        Product product = productService.findProductById(productId);
        if(wishList.getProduct().contains(product)) {
            throw new ProductException("Product already in wishlist");
        } else {
            wishList.getProduct().add(product);
            return wishListRepository.save(wishList);
        }

    }
}
