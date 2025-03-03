package com.example.paymentService.Service;

import java.util.Optional;

import com.example.paymentService.Model.Payment;
import com.example.paymentService.Service.dto.request.AddPaymentRequest;

public interface PaymentService {
    String processPayment(AddPaymentRequest addPaymentRequest);
    Optional<Payment> verifyPayment(String paymentId);
}
