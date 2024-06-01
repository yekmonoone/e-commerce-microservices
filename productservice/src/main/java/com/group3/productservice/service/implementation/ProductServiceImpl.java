package com.group3.productservice.service.implementation;
import com.group3.productservice.entity.Product;
import com.group3.productservice.repository.ProductRepository;
import com.group3.productservice.service.abstraction.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;




    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public Optional<Product> getProductById(int productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteById(int productId) {
        productRepository.deleteById(productId);

    }

    @Override
    public double getProductPriceById(int productId) {
        Product product=productRepository.findById(productId).orElseThrow();
        return product.getProductPrice();
    }

}
