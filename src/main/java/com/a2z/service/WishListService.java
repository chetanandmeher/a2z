package com.a2z.service;

import com.a2z.exception.ProductException;
import com.a2z.model.WishList;

public interface WishListService {
    WishList createWishList(Long userId) throws Exception;

    WishList getWishListByUserId(Long userId);

    WishList addProductToWishList(Long userId, Long productId) throws ProductException;


}
