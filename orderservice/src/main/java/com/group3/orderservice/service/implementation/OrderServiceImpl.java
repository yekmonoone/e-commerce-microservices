package com.group3.orderservice.service.implementation;


import com.group3.orderservice.client.ProductServiceClient;
import com.group3.orderservice.model.Order;
import com.group3.orderservice.model.OrderItem;
import com.group3.orderservice.repository.OrderRepository;
import com.group3.orderservice.service.abstraction.OrderService;
import com.group3.orderservice.service.mapper.OrderMapper;
import com.group3.orderservice.service.request.PlaceOrderRequest;
import com.group3.orderservice.service.response.GetOrderByIdResponse;
import com.group3.orderservice.service.response.GetOrderItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductServiceClient productServiceClient;


    @Override
    public String getUserIdByOrderId(String orderId) {
        Order order =orderRepository.findById(orderId).orElseThrow();
        return order.getUserId();
    }

    @Override
    public String placeOrder(PlaceOrderRequest request) {

        Order order = OrderMapper.INSTANCE.getOrderFromAddRequest(request);

        List<OrderItem> orderItems = request.getOrderItems().stream().map(item -> {
            OrderItem orderItem = OrderMapper.INSTANCE.getOrderItemFromAddRequest(item);
            double price = productServiceClient.getProductPriceById(item.getProductId());
            orderItem.setPrice(price);
            return orderItem;
        }).collect(Collectors.toList());

        order.setItems(orderItems);
        order.setTotalPrice(orderItems.stream().mapToDouble(OrderItem::getPrice).sum());
        orderRepository.save(order);
        return order.getId();
    }

    @Override
    public List<GetOrderByIdResponse> findOrdersByUserId(String userId) {

        List<Order> orders = orderRepository.findByUserId(userId);
        List<GetOrderByIdResponse> orderByUserIdResponses = new ArrayList<>();
        for (Order order : orders) {
            GetOrderByIdResponse getOrderByIdResponse = OrderMapper.INSTANCE.getOrderByIdResponseFromOrder(order);
            List<GetOrderItemResponse> getOrderItemResponses = OrderMapper.INSTANCE.getOrderItemListResponseFromOrderItem(order.getItems());
            getOrderByIdResponse.setOrderItems(getOrderItemResponses);
            orderByUserIdResponses.add(getOrderByIdResponse);

        }
        return orderByUserIdResponses;
    }





    @Override
    public GetOrderByIdResponse getOrderById(String orderId) {
        Order order =orderRepository.findById(orderId).orElseThrow();
        GetOrderByIdResponse getOrderByIdResponse = OrderMapper.INSTANCE.getOrderByIdResponseFromOrder(order);
        List<GetOrderItemResponse> getOrderItemResponses = OrderMapper.INSTANCE.getOrderItemListResponseFromOrderItem(order.getItems());
        getOrderByIdResponse.setOrderItems(getOrderItemResponses);
        return getOrderByIdResponse;
    }


}