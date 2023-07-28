package com.example.shophaven.transformer;

import com.example.shophaven.dto.request.OrderRequestDto;
import com.example.shophaven.dto.response.OrderResponseDto;
import com.example.shophaven.model.OrderEntity;
import com.example.shophaven.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.UUID;

public class OrderTransformer {


    public static OrderEntity orderRequestDtoToOrder(OrderRequestDto orderRequestDto){

        return OrderEntity.builder()
                .orderId(UUID.randomUUID().toString())
                .cardUsed(orderRequestDto.getCardUsed())
                .items(new ArrayList<>())
                .build();
    }

    public static OrderResponseDto orderToOrderResponseDto(OrderEntity order) {

        return OrderResponseDto.builder()
                .customerEmail(order.getCustomer().getEmailId())
                .orderId(order.getOrderId())
                .orderTotal(order.getOrderTotal())
                .orderDate(order.getOrderDate())
                .build();
    }
}
