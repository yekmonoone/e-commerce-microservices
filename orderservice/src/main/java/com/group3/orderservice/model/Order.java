package com.group3.orderservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(value = "orders")
public class Order {
    @Id
    private String id;
    private int userId;
    private List<OrderItem> items;
    private LocalDate orderDate;
    private String status;
    private double totalPrice;
    //TODO:user service eklendikten sonra adress eklenicek

}