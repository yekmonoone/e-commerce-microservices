package com.example.paymentService.Service.Impl;

import com.example.paymentService.Model.Payment;
import com.example.paymentService.Service.PaymentService;
import com.example.paymentService.Service.dto.request.AddOrderItemRequest;
import com.example.paymentService.Service.dto.request.AddPaymentRequest;
import com.example.paymentService.Service.dto.request.PlaceOrderRequest;
import com.example.paymentService.Service.dto.response.GetCartResponse;
import com.example.paymentService.client.CartServiceClient;
import com.example.paymentService.client.OrderServiceClient;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private final CartServiceClient cartServiceClient;

    @Autowired
    private final OrderServiceClient orderServiceClient;

    private final Map<String, Payment> paymentStorage = new HashMap<>();

    public PaymentServiceImpl(CartServiceClient cartServiceClient, OrderServiceClient orderServiceClient) {
        this.cartServiceClient = cartServiceClient;
        this.orderServiceClient = orderServiceClient;
    }

    @Override
    public String processPayment(AddPaymentRequest addPaymentRequest) {
        // Ödeme işleme mantığı burada uygulanacak
        Payment payment=new Payment();
        payment.setPaymentId(UUID.randomUUID().toString());
        payment.setUserId(addPaymentRequest.getUserId());

        payment.setStatus("done");// Basit temsili ödeme
        List<GetCartResponse> cartItems= cartServiceClient.getUserCartById(addPaymentRequest.getUserId());
        PlaceOrderRequest request=new PlaceOrderRequest();
        request.setUserId(addPaymentRequest.getUserId());
        List<AddOrderItemRequest> addOrderItemRequestList=new ArrayList<>();
        for(GetCartResponse item:cartItems){
            AddOrderItemRequest addOrderItemRequest= new AddOrderItemRequest(item.getProductId(),item.getQuantity());
            addOrderItemRequestList.add(addOrderItemRequest);

        }
        request.setOrderItems(addOrderItemRequestList);
        String orderNumber=orderServiceClient.placeOrder(request);

        paymentStorage.put(payment.getPaymentId(), payment);
        return orderNumber;
    }

    @Override
    public Optional<Payment> verifyPayment(String paymentId) {
        // Ödeme detaylarını döndürür
        return Optional.ofNullable(paymentStorage.get(paymentId));
    }
}
