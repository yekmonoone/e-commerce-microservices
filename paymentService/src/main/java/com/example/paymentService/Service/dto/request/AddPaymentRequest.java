package com.example.paymentService.Service.dto.request;

public class AddPaymentRequest {
    private int userId;
    private double amount;
    private String status;

    public AddPaymentRequest(int cartId, double amount, String status) {
        this.userId = cartId;
        this.amount = amount;
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
