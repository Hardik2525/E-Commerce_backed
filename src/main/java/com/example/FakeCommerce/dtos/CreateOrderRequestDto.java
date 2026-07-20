package com.example.FakeCommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.math.*;
import java.util.List;
import com.example.FakeCommerce.schema.OrderStatus;

import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateOrderRequestDto {
    private OrderStatus Status;
    private List<OrderItemRequestDto> orderItems;
}
