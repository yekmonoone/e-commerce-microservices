package com.example.cartservice.repository;


import com.example.cartservice.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByUserId(int userId);
    Cart findByUserIdAndProductId(int userId, int productId);
}

