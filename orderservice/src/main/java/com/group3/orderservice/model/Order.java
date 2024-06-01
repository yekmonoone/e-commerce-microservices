package com.group3.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(value = "orders")
public class Order {
    @Id
    private String id;
    private String userId;
    private List<OrderItem> items;
    private double totalPrice;
    //TODO:user service eklendikten sonra adress eklenicek

}