package com.example.FakeCommerce.adapters;

import com.example.FakeCommerce.dtos.GetCategoryResponseDto;
import com.example.FakeCommerce.dtos.GetOrderResponseDto;
import com.example.FakeCommerce.dtos.OrderItemResponseDto;
import com.example.FakeCommerce.schema.Order;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.FakeCommerce.schema.*;
import com.example.FakeCommerce.repositories.*;


@Component
@RequiredArgsConstructor
public class OrderAdapter {

    private final OrderProductsRepository orderProductsRepository;

    public List<GetOrderResponseDto> mapToGetOrderResponseDtoList(List<Order> orders){
     return orders.stream()
     .map(this::mapToGetOrderResponseDto)
     .collect(Collectors.toList());   
    }

    public  GetOrderResponseDto mapToGetOrderResponseDto(Order order){
        List<OrderProduct> orderProducts = orderProductsRepository.findByOrderId(order.getId());
        List<OrderItemResponseDto> items = mapToOrderItemResponseDtos(orderProducts);
        return GetOrderResponseDto.builder()
        .id(order.getId())
        .status(order.getStatus())
        .createdAt(order.getCreatedAt())
        .updatedAt(order.getUpdatedAt())
        .items(items)
        .build();
    }

    public List<OrderItemResponseDto> mapToOrderItemResponseDtos(List<OrderProduct> orderProducts){
        return orderProducts.stream()
        .map(op->OrderItemResponseDto.builder()
        .productId(op.getProduct().getId())
        .productName(op.getProduct().getTitle())
        .productPrice(op.getProduct().getPrice())
        .productImage(op.getProduct().getImage())
        .quantity(op.getQuantity())
        .subTotal(op.getProduct().getPrice().multiply(BigDecimal.valueOf(op.getQuantity())))
        .build())
        .collect(Collectors.toList());
    }

    public GetCategoryResponseDto mapToCategoryResponseDto(Category category){
        return GetCategoryResponseDto.builder()
               .id(category.getId())
               .name(category.getName())
               .build();
    }

    public List<GetCategoryResponseDto> mapToCategoryResponseDtoList(List<Category> categories){
        return categories.stream()
        .map(this::mapToCategoryResponseDto)
        .collect(Collectors.toList());
    }
}
