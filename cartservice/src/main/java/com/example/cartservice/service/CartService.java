package com.example.cartservice.service;

import com.example.cartservice.model.Cart;
import com.example.cartservice.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public void addToCart(int userId, int productId, int quantity) {
        Cart cart = cartRepository.findByUserIdAndProductId(userId, productId);
        if (cart == null) {
            cart = new Cart();
            cart.setUserId(userId);
            cart.setProductId(productId);
            cart.setQuantity(quantity);
        } else {
            cart.setQuantity(cart.getQuantity() + quantity);
        }
        cartRepository.save(cart);
    }

    public List<Cart> getCart(int userId) {
        return cartRepository.findByUserId(userId);
    }

    public void removeFromCart(int userId, int productId) {
        Cart cart = cartRepository.findByUserIdAndProductId(userId, productId);
        if (cart != null) {
            cartRepository.delete(cart);
        }
    }
}
