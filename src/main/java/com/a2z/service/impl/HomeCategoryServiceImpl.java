package com.a2z.service.impl;

import com.a2z.model.HomeCategory;
import com.a2z.repository.HomeCategoryRepository;
import com.a2z.service.HomeCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeCategoryServiceImpl implements HomeCategoryService {

    private final HomeCategoryRepository homeCategoryRepository;

    @Override
    public HomeCategory createHomeCategory(HomeCategory homeCategory) {
        return homeCategoryRepository.save(homeCategory);
    }

    @Override
    public List<HomeCategory> createCategories(List<HomeCategory> homeCategories) {
        if(homeCategoryRepository.findAll().isEmpty()) {
            return homeCategoryRepository.saveAll(homeCategories);
        }

        return homeCategoryRepository.findAll();
    }

    @Override
    public HomeCategory updateHomeCategory(HomeCategory homeCategory, Long homeCategoryId) {
        HomeCategory existingCategory = homeCategoryRepository.findById(homeCategoryId)
                .orElseThrow(() -> new RuntimeException("Home Category not found with id: " + homeCategoryId));
        if(homeCategory.getImageUrl()!=null){
            existingCategory.setImageUrl(homeCategory.getImageUrl());
        }
        if(homeCategory.getCategoryId()!=null){
            existingCategory.setCategoryId(homeCategory.getCategoryId());
        }
        return homeCategoryRepository.save(existingCategory);
    }

    @Override
    public List<HomeCategory> getAllHomeCategory() {
        return homeCategoryRepository.findAll();
    }
}
