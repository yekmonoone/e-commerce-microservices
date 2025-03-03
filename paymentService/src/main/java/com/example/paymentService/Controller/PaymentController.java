package com.example.paymentService.Controller;

import com.example.paymentService.Service.dto.request.AddPaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.example.paymentService.Service.PaymentService;
import com.example.paymentService.Model.Payment;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    
    
    @PostMapping("/process")
    public String makePayment(@RequestBody AddPaymentRequest addPaymentRequest) {
        return paymentService.processPayment(addPaymentRequest);
    }

    @GetMapping("verify/{id}")
    public Optional<Payment> getPayment(@PathVariable String id) {
        return paymentService.verifyPayment(id);
    }

}
