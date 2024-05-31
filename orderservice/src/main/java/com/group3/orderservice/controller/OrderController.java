package com.group3.orderservice.controller;

import com.group3.orderservice.service.abstraction.OrderService;
import com.group3.orderservice.service.request.PlaceOrderRequest;
import com.group3.orderservice.service.response.GetOrderByIdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @GetMapping("getAccountNumberByOrderId")
    public String getAccountNumberByOrderId(@RequestParam String orderId) {
        return orderService.getUserIdByOrderId(orderId);
    }
    @PostMapping("/placeOrder")
    public String placeOrder(@RequestBody PlaceOrderRequest order) {

        return orderService.placeOrder(order);
    }
    @GetMapping("/{orderId}")
    public GetOrderByIdResponse getOrderById(@PathVariable String orderId) {
        return orderService.getOrderById(orderId);
    }


}
