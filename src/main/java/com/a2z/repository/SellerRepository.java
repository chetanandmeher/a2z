package com.a2z.repository;

import com.a2z.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Chetanand Meher
 */
public interface SellerRepository extends JpaRepository<Seller, Long> {

    Seller findByEmail(String email);
}
