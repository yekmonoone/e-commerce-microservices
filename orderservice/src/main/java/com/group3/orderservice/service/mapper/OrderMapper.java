package com.group3.orderservice.service.mapper;

import com.group3.orderservice.model.Order;
import com.group3.orderservice.model.OrderItem;
import com.group3.orderservice.service.request.AddOrderItemRequest;
import com.group3.orderservice.service.request.PlaceOrderRequest;
import com.group3.orderservice.service.response.GetOrderByIdResponse;
import com.group3.orderservice.service.response.GetOrderItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);




    List<GetOrderItemResponse> getOrderItemListResponseFromOrderItem(List<OrderItem> orderItems);

    @Mapping(source = "id", target = "orderId")
    @Mapping(source = "items", target = "orderItems")
    GetOrderByIdResponse getOrderByIdResponseFromOrder(Order order);

    List<OrderItem> getOrderItemListFromAddRequest(List<AddOrderItemRequest> requests);

    @Mappings({
            @Mapping(source = "orderItems", target = "items")
    })
    Order getOrderFromAddRequest(PlaceOrderRequest request);

    OrderItem getOrderItemFromAddRequest(AddOrderItemRequest item);


}