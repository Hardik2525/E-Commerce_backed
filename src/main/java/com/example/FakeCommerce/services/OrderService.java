package com.example.FakeCommerce.services;
import org.springframework.stereotype.Service;

import com.example.FakeCommerce.dtos.CreateOrderRequestDto;
import com.example.FakeCommerce.dtos.GetOrderResponseDto;
import com.example.FakeCommerce.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import com.example.FakeCommerce.schema.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public List<GetOrderResponseDto> getAllOrders(){
        List<Order> orders = orderRepository.findAll();
        List<GetOrderResponseDto> responseDtos = new ArrayList<>();
        for(Order order : orders){
            GetOrderResponseDto response = GetOrderResponseDto.builder()
            .Status(order.getStatus())
            .build();
            responseDtos.add(response);
        }
        return responseDtos;
    }

    public Order createOrder(CreateOrderRequestDto orderRequestDto){
        Order order = Order.builder()
        .Status(orderRequestDto.getStatus())
        .build();
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }
}
