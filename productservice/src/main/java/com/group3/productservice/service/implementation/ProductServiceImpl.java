package com.group3.productservice.service.implementation;
import com.group3.productservice.entity.Product;
import com.group3.productservice.repository.ProductRepository;
import com.group3.productservice.service.abstraction.ProductService;
import com.group3.productservice.service.dto.request.AddProductRequest;
import com.group3.productservice.service.dto.response.GetProductByIdResponse;
import com.group3.productservice.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;




    /*@Override
    public void addProduct(AddProductRequest addProductRequest) {
        Product product=ProductMapper.INSTANCE.productFromAddRequestToProduct(addProductRequest);

        productRepository.save(product);
    }*/
    @Override
    public void addProduct(AddProductRequest addProductRequest) {
        Product product = new Product();
        product.setName(addProductRequest.getName());
        product.setDescription(addProductRequest.getDescription());
        product.setPrice(addProductRequest.getPrice());
        productRepository.save(product);
    }

    @Override
    public GetProductByIdResponse getProductById(int productId) {
        Product product=productRepository.findById(productId).orElse(null);
        return ProductMapper.INSTANCE.productFromGetProduct(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Optional<Product> existingProduct = productRepository.findById(product.getId());
        if (existingProduct.isPresent()) {
            Product updatedProduct = existingProduct.get();
            updatedProduct.setName(product.getName());
            updatedProduct.setDescription(product.getDescription());
            updatedProduct.setPrice(product.getPrice());
            return productRepository.save(updatedProduct);
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    /*public Product updateProduct(Product product) {
        return productRepository.save(product);
    }*/

    @Override
    public void deleteById(int productId) {
        productRepository.deleteById(productId);

    }

    @Override
    public double getProductPriceById(int productId) {
        Product product=productRepository.findById(productId).orElseThrow();
        return product.getPrice();
    }

    @Override
    public List<GetProductByIdResponse> getAll() {
        List<Product> products=productRepository.findAll();
        return ProductMapper.INSTANCE.getProductListResponseFromProductList(products);
    }

    @Override
    public boolean checkProductExists(int productId) {
        return productRepository.existsById(productId);
    }

}
