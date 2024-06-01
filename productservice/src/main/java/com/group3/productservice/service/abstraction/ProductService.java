package com.group3.productservice.service.abstraction;

import com.group3.productservice.entity.Product;
import com.group3.productservice.service.dto.request.AddProductRequest;
import com.group3.productservice.service.dto.response.GetProductByIdResponse;

import java.util.Optional;

public interface ProductService {
    void addProduct(AddProductRequest addProductRequest);

    GetProductByIdResponse getProductById(int productId);
    Product updateProduct(Product product);
    void deleteById(int productId);

    double getProductPriceById(int productId);

}