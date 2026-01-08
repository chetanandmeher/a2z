package com.a2z.service.impl;

import com.a2z.model.Deal;
import com.a2z.model.HomeCategory;
import com.a2z.repository.DealRepository;
import com.a2z.repository.HomeCategoryRepository;
import com.a2z.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DealServiceImpl implements DealService {

    private final DealRepository  dealRepository;
    private final HomeCategoryRepository homeCategoryRepository;


    @Override
    public List<Deal> getDeals() {
        return dealRepository.findAll();
    }

    @Override
    public Deal createDeal(Deal deal) {
        Optional<HomeCategory> category = homeCategoryRepository.findById(deal.getCategory().getId());
        if(category.isEmpty()) {
            throw new RuntimeException("Category not found");
        }
        Deal newDeal = Deal.builder()
                .category(category.get())
                .discount(deal.getDiscount())
                .build();
        return dealRepository.save(deal);
    }

    @Override
    public Deal updateDeal(Deal deal, Long dealId) {
        Deal existingDeal = dealRepository.findById(dealId)
                .orElseThrow(() -> new RuntimeException("Deal not found"));
        HomeCategory category = homeCategoryRepository.findById(dealId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        if(existingDeal != null){
            if(deal.getDiscount() != null){
                existingDeal.setDiscount(deal.getDiscount());
            }
            if(category!= null){
                existingDeal.setCategory(category);
            }
            return dealRepository.save(existingDeal);
        }

        throw new RuntimeException("Deal not found");
    }

    @Override
    public void deleteDeal(Long dealId) {
        Deal deal = dealRepository.findById(dealId)
                .orElseThrow(() -> new RuntimeException("Deal not found"));
        dealRepository.delete(deal);
    }
}
