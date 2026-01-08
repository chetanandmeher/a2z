package com.a2z.service;

;
import com.a2z.model.Home;
import com.a2z.model.HomeCategory;

import java.util.List;

/**
 * @author Chetanand Meher
 */
public interface HomeService {
    Home createHomePageData(List<HomeCategory> allCategories);

}
