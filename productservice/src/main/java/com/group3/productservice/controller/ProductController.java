/*package com.group3.productservice.controller;

import com.group3.productservice.entity.Product;
import com.group3.productservice.service.abstraction.ProductService;
import com.group3.productservice.service.dto.request.AddProductRequest;
import com.group3.productservice.service.dto.response.GetProductByIdResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;*/
package com.group3.productservice.controller;


import com.group3.productservice.entity.Product;
import com.group3.productservice.service.abstraction.ProductService;
import com.group3.productservice.service.dto.request.AddProductRequest;
import com.group3.productservice.service.dto.response.GetProductByIdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")

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
    public ResponseEntity<Object> addProduct(@RequestBody AddProductRequest addProductRequest){
        productService.addProduct(addProductRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/getAll")
    public List<GetProductByIdResponse> getAllProduct(){
        return  productService.getAll();
    }

   /* @PutMapping("/updateProduct")
    public Product updateProduct(Product product){
        return productService.updateProduct(product);

    }*/
    @PutMapping("/updateProduct")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(product);
        return ResponseEntity.ok(updatedProduct);
    }
    /* @DeleteMapping("deleteByProductId/{productId}")
    public void deleteProductById(@PathVariable int productId){
        productService.deleteById(productId);

    }*/
    @DeleteMapping("/deleteByProductId/{productId}")
    public ResponseEntity<Void> deleteProductById(@PathVariable int productId) {
        productService.deleteById(productId);
        return ResponseEntity.noContent().build(); // 204 No Content status code
    }

}