package com.example.paymentService.Service.dto.request;


import java.util.List;

public class PlaceOrderRequest {
    private int userId;
    private List<AddOrderItemRequest> orderItems;

    public PlaceOrderRequest(int userId, List<AddOrderItemRequest> orderItems) {
        this.userId = userId;
        this.orderItems = orderItems;
    }
    public PlaceOrderRequest() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<AddOrderItemRequest> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<AddOrderItemRequest> orderItems) {
        this.orderItems = orderItems;
    }
}