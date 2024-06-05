package com.example.reviewservice.dto.request;

import lombok.Data;

@Data
public class AddReviewRequest {
    private int productId;
    private int userId;
    private int rating;
    private String comment;

}
