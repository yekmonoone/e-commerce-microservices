package com.group3.productservice.service.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetProductByIdResponse {
    private String name;
    private String description;
    private double price;
}
