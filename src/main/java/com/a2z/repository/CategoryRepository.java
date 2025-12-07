package com.a2z.repository;

import com.a2z.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Chetanand Meher
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    public Category getByCategoryId(String category1);
}
