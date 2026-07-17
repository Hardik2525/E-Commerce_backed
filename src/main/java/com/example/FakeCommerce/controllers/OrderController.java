package com.example.FakeCommerce.controllers;

import com.example.FakeCommerce.dtos.CreateOrderRequestDto;
import com.example.FakeCommerce.dtos.GetOrderResponseDto;
import com.example.FakeCommerce.schema.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.example.FakeCommerce.services.*;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<GetOrderResponseDto> getAllOrders(){
        return orderService.getAllOrders();
    }

    @PostMapping
    public Order createOrder(@RequestBody CreateOrderRequestDto orderRequestDto){
        return orderService.createOrder(orderRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUserId(@PathVariable Long userId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @GetMapping("/{id}/summary")
    public void getOrderSummary(@PathVariable Long id) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
