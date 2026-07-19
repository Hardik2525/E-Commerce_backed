package com.example.FakeCommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemRequestDto {
    private Long productId;
    private Integer quantity;
}
