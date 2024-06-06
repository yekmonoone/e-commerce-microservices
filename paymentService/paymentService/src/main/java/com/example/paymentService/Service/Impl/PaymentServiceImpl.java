package com.example.paymentService.Service.Impl;

import com.example.paymentService.Model.Payment;
import com.example.paymentService.Service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final Map<String, Payment> paymentStorage = new HashMap<>();

    @Override
    public Payment processPayment(Payment payment) {
        // Ödeme işleme mantığı burada uygulanacak
        paymentStorage.put(payment.getPaymentId(), payment);
        return payment;
    }

    @Override
    public Optional<Payment> verifyPayment(String paymentId) {
        // Ödeme detaylarını döndürür
        return Optional.ofNullable(paymentStorage.get(paymentId));
    }
}
