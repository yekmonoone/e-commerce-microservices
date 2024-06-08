package com.example.reviewservice.dto;

import lombok.Data;

@Data
public class ReviewDTO {
    private Long reviewId;
    private String userName;
    private int rating;
    private String comment;


}