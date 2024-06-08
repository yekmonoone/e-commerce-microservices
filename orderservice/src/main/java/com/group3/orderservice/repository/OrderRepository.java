package com.group3.orderservice.repository;

import com.group3.orderservice.model.Order;
import com.group3.orderservice.service.response.GetOrderByIdResponse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {


    List<Order> findByUserId(int userId);
}