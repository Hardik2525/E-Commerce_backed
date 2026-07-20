package com.example.FakeCommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderItemActionDto {
    private Long productId;
    private Integer quantity;
    private OrderItemAction action;
}
