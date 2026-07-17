package com.example.FakeCommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.math.*;

import com.example.FakeCommerce.schema.OrderStatus;

import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateOrderRequestDto {
    private OrderStatus Status;
}
