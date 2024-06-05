package com.example.reviewservice.dto.response;


import lombok.Data;

@Data
public class UpdateReviewResponse {
    private String message;
    private int reviewId;

    public UpdateReviewResponse(String message, int reviewId) {
        this.message = message;
        this.reviewId = reviewId;
    }
}
