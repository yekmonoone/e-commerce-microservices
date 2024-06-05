package com.group3.orderservice.service.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderItemResponse {
    private int productId;
    private String productName;
    private int quantity;
    private double price;
}