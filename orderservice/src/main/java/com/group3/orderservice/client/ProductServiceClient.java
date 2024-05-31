package com.group3.orderservice.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "productservice")
public interface ProductServiceClient {
    @GetMapping("/api/products/price/{productId}")
    double getProductPriceById(@PathVariable("productId") int productId);

}