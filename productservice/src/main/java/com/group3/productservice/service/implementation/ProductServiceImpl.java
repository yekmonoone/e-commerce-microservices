package com.group3.productservice.service.implementation;
import com.group3.productservice.entity.Product;
import com.group3.productservice.repository.ProductRepository;
import com.group3.productservice.service.abstraction.ProductService;
import com.group3.productservice.service.dto.request.AddProductRequest;
import com.group3.productservice.service.dto.response.GetProductByIdResponse;
import com.group3.productservice.service.mapper.ProductMapper;
import com.group3.productservice.service.mapper.ProductMapperImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;




    @Override
    public void addProduct(AddProductRequest addProductRequest) {
        Product product=ProductMapper.INSTANCE.productFromAddRequestToProduct(addProductRequest);

        productRepository.save(product);
    }

    @Override
    public GetProductByIdResponse getProductById(int productId) {
        Product product=productRepository.findById(productId).orElse(null);
        return ProductMapper.INSTANCE.productFromGetProduct(product);
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
        return product.getPrice();
    }

}
