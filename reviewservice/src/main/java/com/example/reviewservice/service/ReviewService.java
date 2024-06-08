package com.example.reviewservice.service;

import com.example.reviewservice.dto.ReviewDTO;
import com.example.reviewservice.model.Review;
import com.example.reviewservice.repository.ReviewRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    private static final String USER_SERVICE_URL = "https://782d1f2a-549a-4bf5-963c-515a174ac12b.mock.pstmn.io/users/";

    public List<ReviewDTO> getReviewsByProductId(Long productId) {
        List<Review> reviews = reviewRepository.findByProductId(productId);

        return reviews.stream().map(review -> {
            ReviewDTO reviewDTO = new ReviewDTO();
            reviewDTO.setReviewId(review.getId());
            reviewDTO.setRating(review.getRating());
            reviewDTO.setComment(review.getComment());

            // User Service'ten kullanıcı adını alma
            String userResponse = webClientBuilder.build()
                    .get()
                    .uri(USER_SERVICE_URL + review.getUserId())
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();


            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = null;
            try {
                rootNode = mapper.readTree(userResponse);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            String username = rootNode.path("username").asText();
            reviewDTO.setUserName(username);
            return reviewDTO;
        }).collect(Collectors.toList());
    }

    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }
}