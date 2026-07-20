package com.example.FakeCommerce.dtos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Builder;

import lombok.Setter;
import java.math.*;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.cglib.core.Local;

import com.example.FakeCommerce.schema.*;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetOrderSummaryResponseDto {
    private Long id;
    private OrderStatus status;
    private List<OrderItemResponseDto> items;
    private Integer totalItems;
    private BigDecimal totalprice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
