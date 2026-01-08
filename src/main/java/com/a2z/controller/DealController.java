package com.a2z.controller;

import com.a2z.model.Deal;
import com.a2z.response.ApiResponse;
import com.a2z.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/deals")
public class DealController {
    private final DealService dealService;

    @PostMapping()
    public ResponseEntity<Deal> updateDeal(
            @RequestBody Deal deal
    )throws Exception{
        Deal createdDeal = dealService.createDeal(deal);
        return ResponseEntity.accepted().body(createdDeal);
    }

    @PatchMapping("/{dealId}")
    public ResponseEntity<Deal> updateDeal(
            @PathVariable Long dealId,
            @RequestBody Deal deal
    )throws Exception {
        Deal updatedDeal = dealService.updateDeal(deal, dealId);
        return ResponseEntity.ok(updatedDeal);
    }

    @DeleteMapping("/{dealId}")
    public ResponseEntity<ApiResponse> deleteDeal(
            @PathVariable Long dealId
    )throws Exception {
        dealService.deleteDeal(dealId);
        ApiResponse response = ApiResponse.builder()
                .message("Deal deleted successfully")
                .build();
        return ResponseEntity.accepted().body(response);
    }



}
