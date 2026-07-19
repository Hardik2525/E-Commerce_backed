package com.example.FakeCommerce.dtos;

import com.example.FakeCommerce.schema.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import lombok.Setter;
import java.math.*;
import java.time.LocalDateTime;
import java.util.List;

import lombok.NoArgsConstructor;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetOrderResponseDto {
    private Long id;
    private OrderStatus status;
    private List<OrderItemResponseDto> items; 
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
