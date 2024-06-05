package com.example.reviewservice.service;

import com.example.reviewservice.dto.ReviewDTO;
import com.example.reviewservice.model.Review;
import com.example.reviewservice.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    private static final String USER_SERVICE_URL = "http://user-service/users/";

    public List<ReviewDTO> getReviewsByProductId(Long productId) {
        List<Review> reviews = reviewRepository.findByProductId(productId);

        return reviews.stream().map(review -> {
            ReviewDTO reviewDTO = new ReviewDTO();
            reviewDTO.setReviewId(review.getId());
            reviewDTO.setRating(review.getRating());
            reviewDTO.setComment(review.getComment());

            // User Service'ten kullanıcı adını alma
            String userName = webClientBuilder.build()
                    .get()
                    .uri(USER_SERVICE_URL + review.getUserId())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            reviewDTO.setUserName(userName);
            return reviewDTO;
        }).collect(Collectors.toList());
    }

    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }
}
