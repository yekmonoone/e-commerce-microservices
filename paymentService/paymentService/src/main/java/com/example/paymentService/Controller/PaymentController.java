package com.example.paymentService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.paymentService.Service.PaymentService;
import com.example.paymentService.Model.Payment;

import java.util.Optional;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/process")
    public Payment makePayment(@RequestBody Payment payment) {
        return paymentService.processPayment(payment);
    }

    @GetMapping("verify/{id}")
    public Optional<Payment> getPayment(@PathVariable String id) {
        return paymentService.verifyPayment(id);
    }
}
