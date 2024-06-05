package com.group3.orderservice.service.abstraction;


import com.group3.orderservice.service.request.PlaceOrderRequest;
import com.group3.orderservice.service.response.GetOrderByIdResponse;

import java.util.List;

public interface OrderService {
    String getUserIdByOrderId(String orderId);

    String placeOrder(PlaceOrderRequest request);

    List<GetOrderByIdResponse> findOrdersByUserId(String userId);

    GetOrderByIdResponse getOrderById(String orderId);
    String getStatus(String orderId);


}