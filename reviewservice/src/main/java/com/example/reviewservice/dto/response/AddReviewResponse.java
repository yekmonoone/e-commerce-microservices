package com.example.reviewservice.dto.response;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class AddReviewResponse {

    private int id;
    private int product_id;
    private int user_id;
    private int rating;
    private String comment;
    private Timestamp created_at;
    private Timestamp updated_at;

    public AddReviewResponse(int id, int product_id, int user_id, int rating, String comment, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.product_id = product_id;
        this.user_id = user_id;
        this.rating = rating;
        this.comment = comment;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}