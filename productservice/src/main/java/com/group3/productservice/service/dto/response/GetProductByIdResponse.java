package com.group3.productservice.service.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetProductByIdResponse {
    private int id;
    private String name;
    private String description;
    private double price;

}
