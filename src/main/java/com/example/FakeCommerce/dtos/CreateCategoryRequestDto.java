package com.example.FakeCommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class CreateCategoryRequestDto {
    private String name;
}

