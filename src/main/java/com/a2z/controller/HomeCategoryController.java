package com.a2z.controller;

import com.a2z.model.HomeCategory;
import com.a2z.service.HomeCategoryService;
import com.a2z.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.a2z.model.Home;
import java.util.List;

/**
 * @author Chetanand Meher
 */
@RequiredArgsConstructor
@RestController
public class HomeCategoryController {

    private final HomeCategoryService homeCategoryService;
    private final HomeService homeService;

    @PostMapping("/home/categories")
    public ResponseEntity<Home> createHomeCategories(
            @RequestBody List<HomeCategory> homeCategories
    ) throws Exception{
        List<HomeCategory> categories = homeCategoryService.createCategories(homeCategories);
        Home home = homeService.createHomePageData(categories);
        return ResponseEntity.accepted().body(home);
    }

    @GetMapping("/admin/home-category")
    public ResponseEntity<List<HomeCategory>> getHomeCategory() throws Exception{
        List<HomeCategory> categories = homeCategoryService.getAllHomeCategory();
        return ResponseEntity.ok(categories);
    }

    @PatchMapping("/admin/home-category/{homeCategoryId}")
    public ResponseEntity<HomeCategory> updateHomeCategory(
            @PathVariable Long homeCategoryId,
            @RequestBody HomeCategory homeCategory) throws Exception{
        HomeCategory updatedCategory = homeCategoryService.updateHomeCategory(homeCategory, homeCategoryId);
        return ResponseEntity.ok(updatedCategory);
    }


}
