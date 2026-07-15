package com.example.FakeCommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetProductWithDetailedResponseDto extends GetProductResponseDto {
    private String category;
}
