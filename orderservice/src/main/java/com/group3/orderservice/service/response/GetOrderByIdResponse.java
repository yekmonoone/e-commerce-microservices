package com.group3.orderservice.service.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderByIdResponse {
    private String orderId;
    private List<GetOrderItemResponse> OrderItems;
    private double totalPrice;
    private LocalDate orderDate;

}