package com.example.FakeCommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateProductRequestDto {
    private Long id;
    private String title;
    private String description;
    private String price;
    private String image;
    private String category;
    private String rating;
}
