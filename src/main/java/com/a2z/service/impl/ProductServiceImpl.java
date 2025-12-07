package com.a2z.service.impl;

import com.a2z.exception.ProductException;
import com.a2z.model.Category;
import com.a2z.model.Product;
import com.a2z.model.Seller;
import com.a2z.repository.CategoryRepository;
import com.a2z.repository.ProductRepository;
import com.a2z.request.CreateProductRequest;
import com.a2z.service.ProductService;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chetanand Meher
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product createProduct(CreateProductRequest request, Seller seller) {

        Category category1 = categoryRepository.getByCategoryId(request.getCategory1());
        if(category1 == null){
            Category category = Category.builder()
                    .categoryId(request.getCategory1())
                    .level(1)
                    .build();
            category1 = categoryRepository.save(category);
        }

        Category category2 = categoryRepository.getByCategoryId(request.getCategory2());
        if(category2 == null){
            Category category = Category.builder()
                    .categoryId(request.getCategory2())
                    .level(2)
                    .parentCategory(category1)
                    .build();
            category2 = categoryRepository.save(category);
        }

        Category category3 = categoryRepository.getByCategoryId(request.getCategory3());
        if(category3 == null){
            Category category = Category.builder()
                    .categoryId(request.getCategory3())
                    .level(3)
                    .parentCategory(category2)
                    .build();
            category3 = categoryRepository.save(category);
        }

        int discountPercentage = calculateDiscountPercentage(request.getMrpPrice(), request.getSellingPrice());
        // create product object
        Product product = Product.builder()
                .seller(seller)
                .title(request.getTitle())
                .description(request.getDescription())
                .mrpPrice(request.getMrpPrice())
                .sellingPrice(request.getSellingPrice())
                .color(request.getColor())
                .images(request.getImages())
                .size(request.getSize())
                .category(category3)
                .createdAt(LocalDateTime.now())
                .discountPercentage(discountPercentage)
                .build();

        return productRepository.save(product);
    }

    private int calculateDiscountPercentage(int mrpPrice, int sellingPrice) {
        if(mrpPrice<=0){
            throw new IllegalArgumentException("MRP Price must be greater than zero");
        }
        double discount = mrpPrice - sellingPrice;
        return (int) ((discount / mrpPrice) * 100);
    }

    @Override
    public void deleteProduct(Long productId) throws ProductException {
        Product product = findProductById(productId);
        productRepository.delete(product);
    }

    @Override
    public Product updateProduct(Long productId, Product updatedProduct) throws ProductException {
        findProductById(productId);
        updatedProduct.setId(productId);
        return productRepository.save(updatedProduct);
    }

    @Override
    public Product findProductById(Long productId) throws ProductException {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductException("Product not found with id: " + productId));
    }

    @Override
    public List<Product> searchProduct(String query) {
        return productRepository.searchProduct(query);
    }

    @Override
    public Page<Product> getAllProducts(String category, String brand, String colors, String sizes, Integer minPrice, Integer maxPrice, String sort, Integer minDiscountPercentage, String stock, Integer pageNumber) {
        Specification<Product> spec = (root, query, criteriaBuilder) ->{
            List<Predicate> predicates = new ArrayList<>();

            if(category != null){
                Join<Product, Category> categoryJoin = root.join("category");
                predicates.add(criteriaBuilder.equal(categoryJoin.get("categoryId"), category));
            }
            if(colors != null && !colors.isEmpty()){
                predicates.add(criteriaBuilder.equal(root.get("color"), colors));
            }
            if(sizes != null && !sizes.isEmpty()){
                predicates.add(criteriaBuilder.equal(root.get("size"), sizes));
            }
            if(minPrice != null){
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("sellingPrice"), minPrice));
            }
            if(maxPrice != null){
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("sellingPrice"), maxPrice));
            }
            if(minDiscountPercentage != null){
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("discountPercentage"), minDiscountPercentage));
            }
            if(stock != null){
                predicates.add(criteriaBuilder.equal(root.get("stock"), stock));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        Pageable pageable;
        if(sort != null && !sort.isEmpty()){
            pageable = switch (sort) {
                case "price_low" -> PageRequest.of(pageNumber != null ? pageNumber : 0,
                        10, Sort.by("sellingPrice").ascending());
                case "price_high" -> PageRequest.of(pageNumber != null ? pageNumber : 0,
                        10, Sort.by("sellingPrice").descending());
                default -> PageRequest.of(pageNumber != null ? pageNumber : 0,
                        10, Sort.unsorted());
            };
        } else{
            pageable = PageRequest.of(pageNumber != null ? pageNumber : 0,
                    10, Sort.unsorted());
        }

        return productRepository.findAll(spec, pageable);
    }

    @Override
    public List<Product> getProductsBySellerId(Long sellerId) {
        return productRepository.findBySellerId(sellerId);
    }
}
