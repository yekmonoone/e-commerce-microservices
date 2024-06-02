package com.inventoryservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "productservice")
public interface ProductServiceClient {
    @GetMapping("/api/products/checkProductExists/{productId}")
    boolean checkProductExists(@PathVariable("productId") int productId);

}
