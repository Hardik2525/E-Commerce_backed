package com.example.FakeCommerce.dtos;

import com.example.FakeCommerce.schema.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateOrderRequestDto {
    private OrderStatus status;
    private List<OrderItemActionDto> orderItems;
}
