package com.a2z.controller;


import com.a2z.exception.ProductException;
import com.a2z.exception.SellerException;
import com.a2z.model.Product;
import com.a2z.model.Seller;
import com.a2z.request.CreateProductRequest;
import com.a2z.service.ProductService;
import com.a2z.service.SellerService;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author Chetanand Meher
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/seller/products")
public class SellerProductController {

    private final SellerService sellerService;
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProductsBySellerId(
            @RequestHeader("Authorization") String jwt
    ) throws SellerException, ProductException {
        Seller seller = sellerService.getSellerProfileByJwt(jwt);
        List<Product> products = productService.getProductsBySellerId(seller.getId());
        return ResponseEntity.ok(products);
    }

    @PostMapping()
    public ResponseEntity<Product> createProduct(
            @RequestBody CreateProductRequest request,
            @RequestHeader("Authorization") String jwt
    ) throws SellerException, ProductException,
            ExecutionControl.UserException {
        Seller seller = sellerService.getSellerProfileByJwt(jwt);
        Product product = productService.createProduct(request, seller);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId
    ) throws SellerException, ProductException
    {
        try{
            productService.deleteProduct(productId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (ProductException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId,
            @RequestBody Product updatedProduct
    ) throws ProductException {
        Product product = productService.updateProduct(productId, updatedProduct);
        return ResponseEntity.ok(product);
    }


















}
