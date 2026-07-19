package com.example.FakeCommerce.controllers;

import com.example.FakeCommerce.dtos.CreateOrderRequestDto;
import com.example.FakeCommerce.dtos.GetOrderResponseDto;
import com.example.FakeCommerce.schema.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.ResponseEntity;
import com.example.FakeCommerce.utils.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;

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
    public ResponseEntity<ApiResponse<List<GetOrderResponseDto>>> getAllOrders(){
        List<GetOrderResponseDto> orders= orderService.getAllOrders();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(orders,"orders fetched successfully"));
    }

    @PostMapping
    public GetOrderResponseDto createOrder(@RequestBody CreateOrderRequestDto orderRequestDto){
        return orderService.createOrder(orderRequestDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(null,"Order deleted successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<GetOrderResponseDto>> getOrderById(@PathVariable Long id) {
        GetOrderResponseDto order = orderService.getOrderByid(id);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(order,"Order fetched successfully"));
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
