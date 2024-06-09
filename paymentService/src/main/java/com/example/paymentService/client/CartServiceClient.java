package com.example.paymentService.client;

import com.example.paymentService.Service.dto.response.GetCartResponse;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "cartservice")
public interface CartServiceClient {
    @GetMapping("/cart/{userId}")
    List<GetCartResponse> getUserCartById(@PathVariable("userId") int userId);
}
