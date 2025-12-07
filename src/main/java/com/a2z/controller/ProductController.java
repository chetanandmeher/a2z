package com.a2z.controller;

import com.a2z.exception.ProductException;
import com.a2z.model.Product;
import com.a2z.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Chetanand Meher
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(
            @Param("productId") Long productId
    ) throws ProductException {
        Product product = productService.findProductById(productId);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProduct(
            @RequestParam(required = false) String query
    ) throws ProductException{
        List<Product> products = productService.searchProduct(query);
        return ResponseEntity.ok(products);
    }

    @GetMapping
    public ResponseEntity<Page<Product>> getAllProduct(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String size,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false) Integer minDiscount,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String stock,
            @RequestParam(defaultValue = "0") Integer pageNumber
    ) throws ProductException{

        Page<Product> products = productService.getAllProducts(
                category, brand, color, size,
                minPrice, maxPrice, sort,
                minDiscount, stock, pageNumber);
        return ResponseEntity.ok(products);
    }

}
