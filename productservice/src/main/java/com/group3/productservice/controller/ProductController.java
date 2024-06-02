package com.group3.productservice.controller;

import com.group3.productservice.entity.Product;
import com.group3.productservice.service.abstraction.ProductService;
import com.group3.productservice.service.dto.request.AddProductRequest;
import com.group3.productservice.service.dto.response.GetProductByIdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{productId}")
    public GetProductByIdResponse getProductById(@PathVariable int productId) {
        return productService.getProductById(productId);
    }
    @GetMapping("/price/{productId}")
    public double getProductPriceById(@PathVariable int productId) {
        return productService.getProductPriceById(productId);
    }
    @PostMapping("/addProduct")
    public void addProduct(@RequestBody AddProductRequest addProductRequest){
        productService.addProduct(addProductRequest);
    }
    @GetMapping("/getAll")
    public List<GetProductByIdResponse> getAllProduct(){
        return  productService.getAll();
    }
    @GetMapping("checkProductExists/{productId}")
    public boolean checkProductExists(@PathVariable("productId") int productId){
        return productService.checkProductExists(productId);
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