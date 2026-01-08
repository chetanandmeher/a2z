package com.a2z.controller;


import com.a2z.model.Product;
import com.a2z.model.Review;
import com.a2z.model.User;
import com.a2z.repository.ReviewRepository;
import com.a2z.request.CreateReviewRequest;
import com.a2z.response.ApiResponse;
import com.a2z.service.ProductService;
import com.a2z.service.ReviewService;
import com.a2z.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Chetanand Meher
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ProductService productService;
    private final ReviewService reviewService;
    private final UserService userService;

    @GetMapping("/products/{productId}/reviews")
    public ResponseEntity<List<Review>> getReviewsByProductId(
            @PathVariable Long productId
    ) throws Exception{
        List<Review> reviews = reviewService.getReviewByProductId(productId);
        return ResponseEntity.ok(reviews);
    }


    @PostMapping("/products/{productId}/reviews")
    public ResponseEntity<Review> addReviewToProduct(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long productId,
            @RequestBody CreateReviewRequest reviewRequest
    ) throws Exception{
        Long userId = userService.getUserIdFromJwt(jwt);
        User user = userService.findUserById(userId);
        Product product = productService.findProductById(productId);
        Review savedReview = reviewService.createReview(reviewRequest, user, product);
        return ResponseEntity.ok(savedReview);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<ApiResponse> deleteReview(
            @PathVariable Long reviewId,
            @RequestHeader("Authorization") String jwt
    ) throws  Exception{
        Long userId = userService.getUserIdFromJwt(jwt);
        reviewService.deleteReview(reviewId, userId);
        ApiResponse response = ApiResponse.builder()
                .message("Review deleted successfully")
                .build();
        return ResponseEntity.ok(response);
    }


}
