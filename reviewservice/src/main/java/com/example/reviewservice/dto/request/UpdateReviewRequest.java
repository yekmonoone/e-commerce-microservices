package com.example.reviewservice.dto.request;

import lombok.Data;

@Data
public class UpdateReviewRequest {
    private int reviewId;
    private int rating;
    private String comment;

}