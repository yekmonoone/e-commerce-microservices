package com.example.cartservice.controller;

import com.example.cartservice.dto.CartDTO;
import com.example.cartservice.model.Cart;
import com.example.cartservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/{userId}/add")
    public ResponseEntity<Void> addToCart(@PathVariable int userId, @RequestBody CartDTO cartDTO) {
        cartService.addToCart(userId, cartDTO.getProductId(), cartDTO.getQuantity());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Cart>> getCart(@PathVariable int userId) {
        List<Cart> cartItems = cartService.getCart(userId);
        return ResponseEntity.ok(cartItems);
    }

    @DeleteMapping("/{userId}/remove/{productId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable int userId, @PathVariable int productId) {
        cartService.removeFromCart(userId, productId);
        return ResponseEntity.ok().build();
    }
}
