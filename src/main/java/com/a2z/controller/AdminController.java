package com.a2z.controller;

import com.a2z.enums.AccountStatus;
import com.a2z.model.Seller;
import com.a2z.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AdminController {

    private final SellerService sellerService;

    @PatchMapping("/seller/{sellerId}/status/{status}")
    public ResponseEntity<Seller> updateSellerAccountStatus(
            @PathVariable Long sellerId,
            @PathVariable AccountStatus status
            )throws Exception{
        Seller updateSeller = sellerService.updateSellerAccountStatus(sellerId, status);
        return ResponseEntity.ok(updateSeller);
    }
}
