package com.a2z.repository;

import com.a2z.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Chetanand Meher
 */
public interface WishListRepository extends JpaRepository<WishList, Long> {

    WishList findByUserId(Long userId);
}
