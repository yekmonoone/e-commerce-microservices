package com.group3.productservice.service.abstraction;

import com.group3.productservice.entity.Product;

import java.util.Optional;

public interface ProductService {
    void addProduct(Product product);

    Optional<Product> getProductById(int productId);
    Product updateProduct(Product product);
    void deleteById(int productId);

    double getProductPriceById(int productId);

}