package com.group3.productservice.controller;

import com.group3.productservice.entity.Product;
import com.group3.productservice.service.abstraction.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{productId}")
    public Optional<Product> getProductById(@PathVariable int productId) {
        return productService.getProductById(productId);
    }
    @GetMapping("/price/{productId}")
    public double getProductPriceById(@PathVariable int productId) {
        return productService.getProductPriceById(productId);
    }
    @PostMapping("/addProduct")
    public void addProduct(@RequestBody Product product){
        productService.addProduct(product);
    }

    @PutMapping("/updateProduct")
    public Product updateProduct(Product product){
        return productService.updateProduct(product);

    }
    @DeleteMapping("deleteByProductId/{productId}")
    public void deleteProductById(@PathVariable int productId){
        productService.deleteById(productId);

    }

}