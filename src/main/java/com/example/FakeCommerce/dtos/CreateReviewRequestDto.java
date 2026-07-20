package com.example.FakeCommerce.dtos;
import java.math.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateReviewRequestDto {
    private BigDecimal rating;
    private String comment;
    private Long productId;
    private Long orderId;
}
