package com.a2z.service;

import com.a2z.model.Deal;

import java.util.List;

public interface DealService {
    List<Deal> getDeals();
    Deal createDeal(Deal deal);
    Deal updateDeal(Deal deal, Long dealId);
    void deleteDeal(Long dealId);

}
