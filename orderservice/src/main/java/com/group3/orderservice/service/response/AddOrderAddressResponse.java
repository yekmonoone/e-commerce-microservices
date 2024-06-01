package com.group3.orderservice.service.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddOrderAddressResponse {
    private String city;
    private String street;
    private Integer flatNumber;
    private String description;
}
