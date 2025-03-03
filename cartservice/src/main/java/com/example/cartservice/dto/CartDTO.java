package com.example.cartservice.dto;

public class CartDTO {
    private int productId;
    private int quantity;

    public CartDTO() {
    }

    public CartDTO(int userId, int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    // Getters and setters

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
