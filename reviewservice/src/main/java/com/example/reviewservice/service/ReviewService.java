package com.example.reviewservice.service;

import com.example.reviewservice.dto.request.AddReviewRequest;
import com.example.reviewservice.dto.request.UpdateReviewRequest;
import com.example.reviewservice.dto.response.AddReviewResponse;
import com.example.reviewservice.dto.response.UpdateReviewResponse;
import com.example.reviewservice.model.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    AddReviewResponse addReview(AddReviewRequest request);
    List<Review> listReviews(int productId, int userId);
    Optional<Review> getReviewById(int id);
    UpdateReviewResponse updateReview(int id, UpdateReviewRequest request);
    void deleteReview(int id);
}
