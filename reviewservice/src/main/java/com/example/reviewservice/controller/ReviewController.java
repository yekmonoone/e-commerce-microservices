package com.example.reviewservice.controller;

import com.example.reviewservice.dto.request.AddReviewRequest;
import com.example.reviewservice.dto.request.UpdateReviewRequest;
import com.example.reviewservice.dto.response.AddReviewResponse;
import com.example.reviewservice.dto.response.UpdateReviewResponse;
import com.example.reviewservice.dto.response.UpdateReviewResponse;
import com.example.reviewservice.model.Review;
import com.example.reviewservice.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping()
    public ResponseEntity<AddReviewResponse> addReview(@RequestBody AddReviewRequest request) {
        AddReviewResponse response = reviewService.addReview(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<List<Review>> listReviews(@RequestParam(required = false) int productId,
                                                    @RequestParam(required = false) int userId) {
        return ResponseEntity.ok(reviewService.listReviews(productId, userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateReviewResponse> updateReview(@PathVariable int id, @RequestBody UpdateReviewRequest request) {
        UpdateReviewResponse response = reviewService.updateReview(id,request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable int id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
