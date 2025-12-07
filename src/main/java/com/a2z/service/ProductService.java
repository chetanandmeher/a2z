package com.a2z.service;

import com.a2z.exception.ProductException;
import com.a2z.model.Product;
import com.a2z.model.Seller;
import com.a2z.request.CreateProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Chetanand Meher
 */
public interface ProductService {

    public Product createProduct(CreateProductRequest request, Seller seller);

    public void deleteProduct(Long productId) throws ProductException;

    public Product updateProduct(Long productId, Product updatedProduct) throws ProductException;

    public Product findProductById(Long productId) throws ProductException;

    public List<Product> searchProduct(String query);

    public Page<Product> getAllProducts(
            String category,
            String brand,
            String colors,
            String sizes,
            Integer minPrice,
            Integer maxPrice,
            String sort,
            Integer minDiscountPercentage,
            String stock,
            Integer pageNumber
    );

    public List<Product> getProductsBySellerId(Long sellerId);




}
