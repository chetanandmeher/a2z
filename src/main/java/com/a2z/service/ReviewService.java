package com.a2z.service;

import com.a2z.model.Product;
import com.a2z.model.Review;
import com.a2z.model.User;
import com.a2z.request.CreateReviewRequest;

import java.util.List;

public interface ReviewService {

    Review createReview(CreateReviewRequest request, User user, Product product);
    List<Review> getReviewByProductId(Long productId);
    Review updateReview(Long reviewId, String reviewText, double reviewRating, Long userId);
    void deleteReview(Long reviewId, Long userId);
    Review getReviewBuId(Long reviewId);