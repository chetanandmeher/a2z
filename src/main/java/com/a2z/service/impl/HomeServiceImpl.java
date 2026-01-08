package com.a2z.service.impl;

import com.a2z.enums.HomeCategorySection;
import com.a2z.model.Deal;
import com.a2z.model.Home;
import com.a2z.model.HomeCategory;
import com.a2z.repository.DealRepository;
import com.a2z.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Chetanand Meher
 */
@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {

    private final DealRepository dealRepository;

    @Override
    public Home createHomePageData(List<HomeCategory> allCategories) {

        List<HomeCategory> gridCategories = allCategories.stream().filter(cat ->
                        HomeCategorySection.GRID == (cat.getSection()))
                .toList();
        List<HomeCategory> shopByCategories = allCategories.stream().filter(cat ->
                        HomeCategorySection.SHOP_BY_CATEGORIES == (cat.getSection()))
                .toList();
        List<HomeCategory> electricCategories = allCategories.stream().filter(cat ->
                        HomeCategorySection.ELECTRIC_CATEGORIES == (cat.getSection()))
                .toList();
        List<HomeCategory> dealCategories = allCategories.stream().filter(cat ->
                        HomeCategorySection.DEALS == (cat.getSection()))
                .toList();

        List<Deal> createDeals = new ArrayList<>();

        if(dealRepository.count() == 0) {
            List<Deal> deals = allCategories.stream()
                    .filter(category -> category.getSection() == HomeCategorySection.DEALS)
                    .map(category -> Deal.builder()
                            .discount(10)
                            .category(category)
                            .build())
                    .toList();
            createDeals = dealRepository.saveAll(deals);
        } else {
            createDeals = dealRepository.findAll();
        }

        return Home.builder()
                .grid(gridCategories)
                .shopByCategory(shopByCategories)
                .deals(createDeals)
                .electricCategory(electricCategories)
                .dealsCategory(dealCategories)
                .build();
    }
}
