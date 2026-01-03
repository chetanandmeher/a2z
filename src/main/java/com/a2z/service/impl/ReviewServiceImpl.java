package com.a2z.service.impl;


import com.a2z.model.Product;
import com.a2z.model.Review;
import com.a2z.model.User;
import com.a2z.repository.ReviewRepository;
import com.a2z.request.CreateReviewRequest;
import com.a2z.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Chetanand Meher
 */
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public Review createReview(CreateReviewRequest request, User user, Product product) {
        Review review = Review.builder()
                .reviewText(request.getReviewText())
                .user(user)
                .product(product)
                .rating(request.getReviewRating())
                .productImages(request.getProductImages())
                .build();

        product.getReviews().add(review);
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getReviewByProductId(Long productId) {
        return reviewRepository.findByProductId(productId);
    }

    @Override
    public Review updateReview(Long reviewId, String reviewText, double reviewRating, Long userId){
        Review review = getReviewBuId(reviewId);
        if(!review.getUser().getId().equals(userId)) {
            throw new RuntimeException("You are not authorized to update this review");
        }
        review.setReviewText(reviewText);
        review.setRating(reviewRating);
        return reviewRepository.save(review);
    }

    @Override
    public void deleteReview(Long reviewId, Long userId) {
        Review review = getReviewBuId(reviewId);
        if(!review.getUser().getId().equals(userId)) {
            throw new RuntimeException("You are not authorized to delete this review");
        }
        reviewRepository.delete(review);
    }

    @Override
    public Review getReviewBuId(Long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(() -> new RuntimeException("Review not found"));
    }
}
