package com.example.paymentService.Service;

import java.util.Optional;

import com.example.paymentService.Model.Payment;

public interface PaymentService {
    Payment processPayment(Payment payment);
    Optional<Payment> verifyPayment(String paymentId);
}
