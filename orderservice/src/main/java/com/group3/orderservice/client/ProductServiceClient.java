package com.group3.orderservice.client;


import com.group3.orderservice.service.response.GetProductByIdResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "productservice")
public interface ProductServiceClient {
    @GetMapping("/api/products/{productId}")
    GetProductByIdResponse getProductById(@PathVariable("productId") int productId);

}