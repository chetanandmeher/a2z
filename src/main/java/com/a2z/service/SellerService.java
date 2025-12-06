package com.a2z.service;

import com.a2z.enums.AccountStatus;
import com.a2z.exception.SellerException;
import com.a2z.model.Seller;

import java.util.List;

/**
 * @author Chetanand Meher
 */
public interface SellerService {

    Seller getSellerProfileByJwt(String jwt);
    Seller createSeller(Seller seller) throws Exception;
    Seller getSellerById(Long id) throws SellerException;
    Seller getSellerByEmail(String email);
    List<Seller> getAllSellerByAccountStatus(AccountStatus status);
    Seller updateSeller(Long id, Seller seller);
    void deleteSeller(Long id) throws SellerException;
    Seller verifyEmail(String email, String otp);
    Seller updateSellerAccountStatus(Long sellerId, AccountStatus status) throws SellerException;

}
