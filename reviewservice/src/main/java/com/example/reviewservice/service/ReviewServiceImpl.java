package com.example.reviewservice.service;

import com.example.reviewservice.dto.request.AddReviewRequest;
import com.example.reviewservice.dto.request.UpdateReviewRequest;
import com.example.reviewservice.dto.response.AddReviewResponse;
import com.example.reviewservice.dto.response.UpdateReviewResponse;
import com.example.reviewservice.model.Review;
import com.example.reviewservice.repository.ReviewRepository;
import com.example.reviewservice.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public AddReviewResponse addReview(AddReviewRequest request) {
        Review review = new Review();
        review.setProductId(request.getProductId());
        review.setUserId(request.getUserId());
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        review.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        reviewRepository.save(review);
        return new AddReviewResponse(
                review.getId(),
                review.getProductId(),
                review.getUserId(),
                review.getRating(),
                review.getComment(),
                review.getCreatedAt(),
                review.getUpdatedAt()
        );
    }

    @Override
    public List<Review> listReviews(int productId, int userId) {
        if (productId != 0) {
            return reviewRepository.findByProductId(productId);
        } else if (userId != 0) {
            return reviewRepository.findByUserId(userId);
        } else {
            return reviewRepository.findAll();
        }
    }

    @Override
    public Optional<Review> getReviewById(int id) {
        return reviewRepository.findById(id);
    }

    @Override
    public UpdateReviewResponse updateReview(int id, UpdateReviewRequest request) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        reviewRepository.save(review);
        return new UpdateReviewResponse("Review updated successfully", review.getId());
    }

    @Override
    public void deleteReview(int id) {
        reviewRepository.deleteById(id);
    }
}
