package com.example.paymentService.client;

import com.example.paymentService.Service.dto.request.PlaceOrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "orderservice")
public interface OrderServiceClient {
    @PostMapping("/api/orders/placeOrder")
    public String placeOrder(@RequestBody PlaceOrderRequest order);


}
