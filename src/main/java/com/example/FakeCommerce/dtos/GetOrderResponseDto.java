package com.example.FakeCommerce.dtos;

import com.example.FakeCommerce.schema.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import lombok.Setter;
import java.math.*;

import lombok.NoArgsConstructor;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetOrderResponseDto {
    private OrderStatus Status;
}
